package com.buzzvil.android.favorite

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.buzzvil.android.databinding.ItemFavoriteSliderBinding
import com.buzzvil.core.database.CampaignEntity

class FavoriteSliderAdapter(
    private var list: List<CampaignEntity>,
    val eventBackground: () -> Unit
) : RecyclerView.Adapter<FavoriteSliderAdapter.ViewPagerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {

        val binding = ItemFavoriteSliderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewPagerViewHolder,
        position: Int
    ) {
//        holder.setIsRecyclable(false)
        holder.setData(list[position])
    }

    fun setData(list: List<CampaignEntity>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class ViewPagerViewHolder(private val binding: ItemFavoriteSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(campaignEntity: CampaignEntity) {
//            Log.d("FavoriteSliderAdapter", "campaignEntity $campaignEntity")
            binding.background.setImageBitmap(campaignEntity.bitmap)

            if (campaignEntity.bitmap != null) {
                Glide.with(binding.root.context)
                    .asBitmap()
                    .load(campaignEntity.bitmap ?: campaignEntity.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(binding.background)
            } else {
                Glide.with(binding.root.context)
                    .load(campaignEntity.imageUrl)
                    .into(binding.background)
            }
            binding.textView.text = campaignEntity.name
            binding.background.setOnClickListener { eventBackground() }
        }

    }
}