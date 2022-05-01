package com.buzzvil.campaign.data.source

import androidx.lifecycle.LiveData
import com.buzzvil.campaign.domain.model.CampaignEntity
import com.buzzvil.campaign.data.Result

interface CampaignsRepository {

    suspend fun getConfig(): Result<String>

    fun observeAds(): LiveData<Result<List<CampaignEntity>>>

    suspend fun getAds(): Result<List<CampaignEntity>>

    suspend fun refreshAds()

    suspend fun saveAds(campaignEntities: List<CampaignEntity>)

    fun observeArticles(): LiveData<Result<List<CampaignEntity>>>

    suspend fun getArticles(): Result<List<CampaignEntity>>

    suspend fun refreshArticles()

    suspend fun saveArticles(campaignEntities: List<CampaignEntity>)

}