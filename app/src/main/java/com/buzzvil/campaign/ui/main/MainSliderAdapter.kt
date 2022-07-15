package com.buzzvil.campaign.ui.main

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.buzzvil.campaign.databinding.ItemMainSliderBinding
import com.buzzvil.campaign.domain.model.CampaignEntity

class MainSliderAdapter : ListAdapter<CampaignEntity, MainSliderAdapter.ViewPagerViewHolder>(
    CampaignDiffer
) {

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
        holder.setData(currentList[position])
    }

    override fun getItemCount(): Int = currentList.size

    inner class ViewPagerViewHolder(private val binding: ItemMainSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(campaignEntity: CampaignEntity) {

            Log.d("TEST", "campaign $campaignEntity")
            setImageResource(campaignEntity.bitmap)
            binding.textView.text = campaignEntity.name
        }

        fun setImageResource(bitmap: Bitmap) {
            Glide.with(binding.root.context)
                .asBitmap()
                .load(bitmap)
//                .error(R.drawable.ic_baseline_error_outline_24)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        var byteCount = Int.MIN_VALUE
                        byteCount = resource.allocationByteCount
                        val sizeInKB = byteCount / 1024
                        val sizeInMB = sizeInKB / 1024
                        Log.d(
                            "TEST",
                            "kb : $sizeInKB ,\n" +
                                    "mb : $sizeInMB"
                        )
                        binding.imageView.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }

    }
}