package com.buzzvil.campaign.data

import android.graphics.Bitmap
import com.buzzvil.campaign.domain.model.Ad
import com.buzzvil.campaign.domain.model.Campaign
import com.buzzvil.campaign.domain.model.CampaignEntity

object CampaignMapper {
    fun adToCampaign(ad: Ad, bitmap: Bitmap?): Campaign {
        return Campaign(
            id = ad.id,
            name = ad.name,
            imageUrl = ad.imageUrl,
            bitmap = bitmap,
            firstDisplayPriority = ad.firstDisplayPriority,
            firstDisplayWeight = ad.firstDisplayWeight
        )
    }
    fun campaignToCampaignEntity(campaign: Campaign): CampaignEntity {
        return CampaignEntity(
            id = campaign.id,
            name = campaign.name,
            imageUrl = campaign.imageUrl,
            bitmap = campaign.bitmap,
            firstDisplayPriority = campaign.firstDisplayPriority,
            firstDisplayWeight = campaign.firstDisplayWeight,
            subscribe = false
        )
    }
}