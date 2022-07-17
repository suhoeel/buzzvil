package com.buzzvil.android.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.buzzvil.android.R
import com.buzzvil.android.databinding.ItemMainSliderBinding
import com.buzzvil.core.database.CampaignEntity

class MainSliderAdapter(
    private var list: List<CampaignEntity>,
    val eventBackground: (campaign: CampaignEntity) -> Unit,
    val eventFavorite: (campaign: CampaignEntity) -> Unit
) : RecyclerView.Adapter<MainSliderAdapter.ViewPagerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerViewHolder {

        val binding = ItemMainSliderBinding.inflate(
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

    inner class ViewPagerViewHolder(private val binding: ItemMainSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(campaignEntity: CampaignEntity) {
//            Log.d("MainSliderAdapter", "campaignEntity $campaignEntity")
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
            binding.background.setOnClickListener { eventBackground(campaignEntity) }

            if(campaignEntity.isFavorite) binding.btFavorite.setImageResource(R.drawable.ic_favorite_true)
            else binding.btFavorite.setImageResource(R.drawable.ic_favorite_false)

            binding.btFavorite.setOnClickListener {
                campaignEntity.isFavorite = !campaignEntity.isFavorite
                if(campaignEntity.isFavorite) binding.btFavorite.setImageResource(R.drawable.ic_favorite_true)
                else binding.btFavorite.setImageResource(R.drawable.ic_favorite_false)
                eventFavorite(campaignEntity)
            }
            /*Glide.with(binding.root.context)
                .load(campaignEntity.bitmap ?: campaignEntity.imageUrl)
//                .error(R.drawable.ic_baseline_error_outline_24)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.imageView)*/
            /*.into(object : CustomTarget<Bitmap>() {
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
            })*/
        }

    }
}