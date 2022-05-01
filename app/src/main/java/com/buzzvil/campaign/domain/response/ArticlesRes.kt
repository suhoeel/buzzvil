package com.buzzvil.campaign.domain.response

import com.buzzvil.campaign.domain.model.Ad
import com.buzzvil.campaign.domain.model.Article

data class ArticlesRes(
    val campaigns: List<Article>
)
