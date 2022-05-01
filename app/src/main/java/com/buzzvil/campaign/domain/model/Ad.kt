package com.buzzvil.campaign.domain.model

data class Ad(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val firstDisplayPriority: Int,
    val firstDisplayWeight: Int
)