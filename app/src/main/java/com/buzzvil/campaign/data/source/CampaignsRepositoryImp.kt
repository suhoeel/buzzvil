package com.buzzvil.campaign.data.source

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LiveData
import com.buzzvil.campaign.data.CampaignMapper
import com.buzzvil.campaign.data.Result
import com.buzzvil.campaign.data.source.local.CampaignsDao
import com.buzzvil.campaign.di.IODispatcher
import com.buzzvil.campaign.domain.model.CampaignEntity
import com.buzzvil.campaign.domain.response.base.NetworkResponse
import com.buzzvil.campaign.network.CampaignsApi
import kotlinx.coroutines.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.URL
import javax.inject.Inject


class CampaignsRepositoryImp @Inject constructor(
    private val campaignsApi: CampaignsApi,
    private val campaignsDao: CampaignsDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : CampaignsRepository {

    override suspend fun getConfig(): Result<String> = withContext(ioDispatcher) {
        val res = campaignsApi.getConfig()
        when (res) {
            is NetworkResponse.Success -> {
                return@withContext Result.Success(res.body.firstAdRatio)
            }
            is NetworkResponse.ApiError -> {
//                response.code
                return@withContext Result.Error(Exception("getConfig() api error code: ${res.code}, body: ${res.body}"))
            }
            is NetworkResponse.NetworkError -> {
                Result.Error(IOException("Internet unavailable"))
            }
            is NetworkResponse.UnknownError -> {
                return@withContext Result.Error(Exception("unknown error, msg: ${res.error}"))
            }
        }
        throw IllegalStateException()
    }

    override fun observeAds(): LiveData<Result<List<CampaignEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAds(): Result<List<CampaignEntity>> = withContext(ioDispatcher) {
        /*val currentAds = campaignsDao.getAll()
        if (currentAds.isNotEmpty()) {
            Log.d("TEST", "disk cached")
            return@withContext Result.Success(currentAds)
        }*/
        val res = campaignsApi.getAds()
        when (res) {
            is NetworkResponse.Success -> {
                val campaignEntities = ArrayList<CampaignEntity>()
                val ads = res.body.campaigns
                ads.map {
                    async {
                        Log.d("TEST", "ad name ${it.name}")
                        val bitmap = downloadImageFromUrl(it.imageUrl)
                        Log.d("TEST", "bitmap width ${bitmap.width}")
                        Log.d("TEST", "bitmap height ${bitmap.height}")
                        val campaign = CampaignMapper.adToCampaign(it, bitmap)
                        campaignEntities.add(CampaignMapper.campaignToCampaignEntity(campaign))
                        Log.d("TEST", "currentThread ${Thread.currentThread().name}")
                    }
                }.awaitAll()
                campaignsDao.insertAll(campaignEntities)
                return@withContext Result.Success(campaignEntities)
            }
            is NetworkResponse.ApiError -> {
//                response.code
                return@withContext Result.Error(Exception("getAds() api error code: ${res.code}, body: ${res.body}"))
            }
            is NetworkResponse.NetworkError -> {
                Result.Error(IOException("Internet unavailable"))
            }
            is NetworkResponse.UnknownError -> {
                return@withContext Result.Error(Exception("unknown error, msg: ${res.error}"))
            }
        }
        throw IllegalStateException()
    }

    override suspend fun refreshAds() {
        TODO("Not yet implemented")
    }

    /*private suspend fun getImageFromUrl(url: String): Bitmap? = withContext(ioDispatcher) {
        val isSuccess = campaignsApi.downloadImage(url)
        if (isSuccess is NetworkResponse.Success) {
//                Log.d("TEST", "image : ${i.name}, byte : ${isSuccess.body.byteStream()}")
            return@withContext BitmapFactory.decodeStream(isSuccess.body.byteStream())
        }
        return@withContext null
    }*/

    private fun downloadImageFromUrl(url: String): Bitmap {
        return BitmapFactory.decodeStream(URL(url).openConnection().getInputStream())
    }

    override suspend fun saveAds(campaignEntities: List<CampaignEntity>) {
        TODO("Not yet implemented")
    }

    override fun observeArticles(): LiveData<Result<List<CampaignEntity>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getArticles(): Result<List<CampaignEntity>> = withContext(ioDispatcher) {
        TODO("Not yet implemented")

        /*val res = campaignsApi.getArticles()
        when (res) {
            is NetworkResponse.Success -> {
                res.body.campaignEntities
                val list = MutableList()
                return@withContext Result.Success()
            }
            is NetworkResponse.ApiError -> {
//                response.code
                return@withContext Result.Error(Exception("getAds() api error code: ${res.code}, body: ${res.body}"))
            }
            is NetworkResponse.NetworkError -> {
                Result.Error(IOException("Internet unavailable"))
            }
            is NetworkResponse.UnknownError -> {
                return@withContext Result.Error(Exception("unknown error, msg: ${res.error}"))
            }
        }
        throw IllegalStateException()*/
    }

    override suspend fun refreshArticles() {
        TODO("Not yet implemented")
    }

    override suspend fun saveArticles(campaignEntities: List<CampaignEntity>) {
        TODO("Not yet implemented")
    }

    /*private suspend fun updateTasksFromRemoteDataSource() {
         val remoteTasks = tasksRemoteDataSource.getTasks()

         if (remoteTasks is Success) {
             // Real apps might want to do a proper sync, deleting, modifying or adding each task.
             tasksLocalDataSource.deleteAllTasks()
             remoteTasks.data.forEach { task ->
                 tasksLocalDataSource.saveTask(task)
             }
         } else if (remoteTasks is Result.Error) {
             throw remoteTasks.exception
         }
     }*/


    /*private suspend fun updateTaskFromRemoteDataSource(taskId: String) {
        val remoteTask = tasksRemoteDataSource.getTask(taskId)

        if (remoteTask is Success) {
            tasksLocalDataSource.saveTask(remoteTask.data)
        }
    }*/

}