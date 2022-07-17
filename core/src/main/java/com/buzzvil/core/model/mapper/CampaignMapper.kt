package com.buzzvil.core.model.mapper

import com.buzzvil.core.database.AdEntity
import com.buzzvil.core.database.ArticleEntity
import com.buzzvil.core.database.CampaignEntity

object CampaignMapper {
    fun adEntityToCampaign (ad: AdEntity): CampaignEntity {
        return CampaignEntity(
            id = ad.id,
            name = ad.name,
            imageUrl = ad.imageUrl,
            firstDisplayPriority = ad.firstDisplayPriority,
            firstDisplayWeight = ad.firstDisplayWeight
        )
    }
    fun articleEntityToCampaign(article: ArticleEntity): CampaignEntity {
        return CampaignEntity(
            id = article.id,
            name = article.name,
            imageUrl = article.imageUrl,
            firstDisplayPriority = article.firstDisplayPriority,
            firstDisplayWeight = article.firstDisplayWeight,
        )
    }
}