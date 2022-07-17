package com.buzzvil.android.main

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule


@GlideModule
class GlideAppModule : AppGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)

        registry.append(Bitmap::class.java, BitmapEncoder())
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        val memoryCacheSizeBytes = 1024 * 1024 * 36 // 20mb
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))

        Log.d("TEST", "called glide")
        val bitmapPoolSizeBytes = 1024 * 1024 * 36 // 36mb
        builder.setBitmapPool(LruBitmapPool(bitmapPoolSizeBytes.toLong()))

        val diskCacheSizeBytes = 1024 * 1024 * 50 // 100 MB
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))
    }

}