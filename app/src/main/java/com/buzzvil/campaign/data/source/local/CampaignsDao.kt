package com.buzzvil.campaign.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.buzzvil.campaign.domain.model.CampaignEntity

@Dao
abstract class CampaignsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(campaignEntities: List<CampaignEntity>)

    @Query(
        "SELECT * FROM CampaignEntity"
    )
    abstract suspend fun getAll(): List<CampaignEntity>

}