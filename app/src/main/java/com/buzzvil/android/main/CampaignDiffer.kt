package com.buzzvil.android.main

import androidx.recyclerview.widget.DiffUtil
import com.buzzvil.core.database.AdEntity
import com.buzzvil.core.database.CampaignEntity

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