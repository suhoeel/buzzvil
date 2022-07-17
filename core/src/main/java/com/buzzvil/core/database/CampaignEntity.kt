package com.buzzvil.core.database

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class CampaignEntity(
    @PrimaryKey(autoGenerate = true) val pk: Int = 0,
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "bitmap") var bitmap: Bitmap? = null,
    @ColumnInfo(name = "firstDisplayPriority") val firstDisplayPriority: Int,
    @ColumnInfo(name = "firstDisplayWeight") val firstDisplayWeight: Int,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false,
    @ColumnInfo(name = "isShowing") var isShowing: Boolean = true,
) : Parcelable {
    /*override fun equals(o: Any?): Boolean {
        if (o === this) return true
        if (o !is CampaignEntity) return false
        return ((id == o.id) and
        (name == o.name) and
        (firstDisplayPriority == o.firstDisplayPriority) and
        (firstDisplayWeight == o.firstDisplayWeight) and
        (subscribe == o.subscribe))
    }

    override fun hashCode(): Int {
        var result = 17
        result = 31 * result + id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + firstDisplayPriority.hashCode()
        result = 31 * result + firstDisplayWeight.hashCode()
        result = 31 * result + subscribe.hashCode()
        return result
    }*/
}