package com.buzzvil.core.network.response

import com.buzzvil.core.database.ArticleEntity

data class ArticlesRes(
    val campaigns: List<ArticleEntity>
)