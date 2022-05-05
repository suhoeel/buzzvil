package com.buzzvil.campaign.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.buzzvil.campaign.domain.model.CampaignEntity

object CampaignDiffer : DiffUtil.ItemCallback<CampaignEntity>() {

    override fun areItemsTheSame(
        oldItem: CampaignEntity,
        newItem: CampaignEntity
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CampaignEntity,
        newItem: CampaignEntity
    ): Boolean {
        return oldItem == newItem
    }

}