package com.buzzvil.core.database

import androidx.room.*
import kotlinx.coroutines.Deferred

@Dao
abstract class CampaignsDao {

    @Insert
    abstract suspend fun insertAds(Ads: List<AdEntity>)

    @Insert
    abstract suspend fun insertArticles(Ads: List<ArticleEntity>)

    @Insert
    abstract suspend fun insertCampaign(campaign: List<CampaignEntity>)

    @Update
    abstract suspend fun updateCampaign(campaign: CampaignEntity): Int


    @Query("DELETE FROM AdEntity")
    abstract suspend fun clearAds()

    @Query("DELETE FROM ArticleEntity")
    abstract suspend fun clearArticles()

    @Query("DELETE FROM CampaignEntity")
    abstract suspend fun clearCampaigns()

    @Delete
    abstract suspend fun removeCampaign(id: CampaignEntity)

    @Query(
        "SELECT * FROM AdEntity"
    )
    abstract suspend fun getAds(): List<AdEntity>

    @Query(
        "SELECT * FROM ArticleEntity"
    )
    abstract suspend fun getArticles(): List<ArticleEntity>

    @Query(
        "SELECT * FROM CampaignEntity WHERE isShowing = 1"
    )
    abstract suspend fun getCampaigns(): List<CampaignEntity>

    @Query(
        "SELECT * FROM CampaignEntity WHERE isFavorite = 1"
    )
    abstract suspend fun getFavorites(): List<CampaignEntity>





}