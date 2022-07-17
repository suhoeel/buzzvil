package com.buzzvil.core.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "firstDisplayPriority") val firstDisplayPriority: Int,
    @ColumnInfo(name = "firstDisplayWeight") val firstDisplayWeight: Int
)