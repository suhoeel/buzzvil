package com.buzzvil.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.buzzvil.core.model.converter.BitmapConverter

@Database(
    entities = [
        AdEntity::class,
        ArticleEntity::class,
        CampaignEntity::class
    ],
    version = 1

)
@TypeConverters(BitmapConverter::class)
abstract class BuzzvilDatabase : RoomDatabase() {
    abstract fun campaignsDao(): CampaignsDao
}