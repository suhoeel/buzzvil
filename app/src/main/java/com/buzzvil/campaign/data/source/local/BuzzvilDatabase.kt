package com.buzzvil.campaign.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.buzzvil.campaign.domain.model.CampaignEntity

@Database(
    entities = [
        CampaignEntity::class
    ],
    version = 1

)
@TypeConverters(BitmapConverter::class)
abstract class BuzzvilDatabase : RoomDatabase() {
    abstract fun campaignsDao(): CampaignsDao
}