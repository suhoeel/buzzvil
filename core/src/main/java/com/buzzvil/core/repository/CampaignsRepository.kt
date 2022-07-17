package com.buzzvil.core.repository

import com.buzzvil.core.database.AdEntity
import com.buzzvil.core.database.ArticleEntity
import com.buzzvil.core.database.CampaignEntity
import com.buzzvil.core.network.Result

interface CampaignsRepository {

    suspend fun getConfig(): Result<String>

    suspend fun fetchAds(): Result<List<AdEntity>>

    suspend fun getAds(): List<AdEntity>

    suspend fun saveAds(ads: List<AdEntity>)

//    fun observeArticles(): LiveData<Result<List<CampaignEntity>>>

    suspend fun fetchArticles(): Result<List<ArticleEntity>>

    suspend fun getArticles(): List<ArticleEntity>

    suspend fun saveArticles(articles: List<ArticleEntity>)

    suspend fun getCampaigns(): List<CampaignEntity>

    suspend fun saveCampaigns(campaign: List<CampaignEntity>)

    suspend fun updateCampaign(campaign: CampaignEntity): Int

    suspend fun getFavorites(): List<CampaignEntity>

}