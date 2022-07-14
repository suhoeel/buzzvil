package com.buzzvil.campaign.domain.model

import android.graphics.Bitmap

data class Campaign(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val bitmap: Bitmap,
    val firstDisplayPriority: Int,
    val firstDisplayWeight: Int
)