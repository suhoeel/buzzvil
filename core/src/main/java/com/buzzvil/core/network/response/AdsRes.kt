package com.buzzvil.core.network.response

import com.buzzvil.core.database.AdEntity

data class AdsRes(
    val campaigns: List<AdEntity>
)