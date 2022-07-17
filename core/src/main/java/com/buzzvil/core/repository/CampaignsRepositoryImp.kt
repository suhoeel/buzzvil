package com.buzzvil.core.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.buzzvil.core.database.*
import com.buzzvil.core.network.Result
import com.buzzvil.core.di.scope.ApplicationScope
import com.buzzvil.core.domain.response.base.NetworkResponse
import com.buzzvil.core.network.CampaignsApi
import com.buzzvil.core.di.scope.IODispatcher
import com.buzzvil.core.model.mapper.CampaignMapper
import kotlinx.coroutines.*
import java.io.IOException
import java.net.URL
import javax.inject.Inject

class CampaignsRepositoryImp @Inject constructor(
    private val campaignsApi: CampaignsApi,
    private val campaignsDao: CampaignsDao,
    @ApplicationScope private val externalScope: CoroutineScope,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : CampaignsRepository {

    companion object {
        private const val TAG = "CampaignsRepositoryImp"
    }

    override suspend fun getConfig(): Result<String> = withContext(ioDispatcher) {
        when (val res = campaignsApi.getConfig()) {
            is NetworkResponse.Success -> Result.Success(res.body.firstAdRatio)
            is NetworkResponse.ApiError -> Result.Error(Exception("getConfig() api error code: ${res.code}, body: ${res.body}"))
            is NetworkResponse.NetworkError -> Result.Error(IOException("Internet unavailable"))
            is NetworkResponse.UnknownError -> Result.Error(Exception("unknown error, msg: ${res.error}"))
        }
    }

    override suspend fun fetchAds(): Result<List<AdEntity>> = withContext(ioDispatcher) {
        when (val res = campaignsApi.getAds()) {
            is NetworkResponse.Success -> {
                val ads = res.body.campaigns
//                Log.d(TAG, "ads ${ads}")

                saveAds(ads)
                Result.Success(ads)
            }
            is NetworkResponse.ApiError -> Result.Error(Exception("getAds() api error code: ${res.code}, body: ${res.body}"))
            is NetworkResponse.NetworkError -> Result.Error(IOException("Internet unavailable"))
            is NetworkResponse.UnknownError -> Result.Error(Exception("unknown error, msg: ${res.error}"))
        }
    }


    override suspend fun getAds(): List<AdEntity> = withContext(ioDispatcher) {
        campaignsDao.getAds()
    }

    private fun downloadImageFromUrl(url: String): Bitmap? {
        try {
            return BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
        } catch (e: Exception) {
        }
        return null
    }

    override suspend fun saveAds(ads: List<AdEntity>) = withContext(ioDispatcher) {
        campaignsDao.clearAds()
        campaignsDao.insertAds(ads)
    }

    override suspend fun fetchArticles(): Result<List<ArticleEntity>> = withContext(ioDispatcher) {
        return@withContext when (val res = campaignsApi.getArticles()) {
            is NetworkResponse.Success -> {
                val articles = res.body.campaigns
//                Log.d(TAG, "articles ${campaignsDao.getArticles()}")
                saveArticles(articles)
                Result.Success(articles)
            }
            is NetworkResponse.ApiError -> Result.Error(Exception("getAds() api error code: ${res.code}, body: ${res.body}"))
            is NetworkResponse.NetworkError -> Result.Error(IOException("Internet unavailable"))
            is NetworkResponse.UnknownError -> Result.Error(Exception("unknown error, msg: ${res.error}"))
        }
    }

    override suspend fun getArticles(): List<ArticleEntity> = withContext(ioDispatcher) {
        campaignsDao.getArticles()
    }


    override suspend fun saveArticles(articles: List<ArticleEntity>) {
        campaignsDao.clearArticles()
        campaignsDao.insertArticles(articles)
    }

    override suspend fun getCampaigns(): List<CampaignEntity> = withContext(ioDispatcher) {
        return@withContext campaignsDao.getCampaigns()
    }

    override suspend fun saveCampaigns(campaign: List<CampaignEntity>) {
        externalScope.launch(ioDispatcher) {
            campaignsDao.clearCampaigns()
            delay(1000L)
            campaign.map {
                async {
                    it.bitmap = downloadImageFromUrl(it.imageUrl)
//                    Log.d(TAG, "ad name ${it.name}")
//                    Log.d(TAG, "bitmap width ${it.bitmap?.width}")
//                    Log.d(TAG, "bitmap height ${it.bitmap?.height}")
//                    Log.d(TAG, "currentThread ${Thread.currentThread().name}")
                }
            }.awaitAll()
            Log.d(TAG, "will save campaign ${campaign}")
            campaignsDao.insertCampaign(campaign)
            Log.d(TAG, "will save campaign ${campaignsDao.getCampaigns()}")
        }
    }

    override suspend fun updateCampaign(campaign: CampaignEntity): Int = withContext(ioDispatcher) {
        campaignsDao.updateCampaign(campaign)
    }

    override suspend fun getFavorites(): List<CampaignEntity> = withContext(ioDispatcher) {
        campaignsDao.getFavorites()
    }


}