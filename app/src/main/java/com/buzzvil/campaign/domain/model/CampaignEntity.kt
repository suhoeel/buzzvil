package com.buzzvil.campaign.domain.model

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CampaignEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "bitmap") val bitmap: Bitmap,
    @ColumnInfo(name = "firstDisplayPriority") val firstDisplayPriority: Int,
    @ColumnInfo(name = "firstDisplayWeight") val firstDisplayWeight: Int,
    @ColumnInfo(name = "subscribe") val subscribe: Boolean
)