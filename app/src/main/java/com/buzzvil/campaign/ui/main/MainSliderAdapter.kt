package com.buzzvil.campaign.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.buzzvil.campaign.domain.model.CampaignEntity
import com.buzzvil.campaign.databinding.ItemMainSliderBinding

class MainSliderAdapter(
    private val data: List<CampaignEntity>
) : RecyclerView.Adapter<MainSliderAdapter.ViewPagerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainSliderAdapter.ViewPagerViewHolder {

        val binding = ItemMainSliderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MainSliderAdapter.ViewPagerViewHolder,
        position: Int
    ) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewPagerViewHolder(val binding: ItemMainSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(campaignEntity: CampaignEntity) {

            Log.d("TEST", "campaign $campaignEntity")
            Glide.with(binding.root.context)
                .load(campaignEntity.bitmap ?: campaignEntity.imageUrl)
//                .error(R.drawable.ic_baseline_error_outline_24)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imageView)

        }

    }
}