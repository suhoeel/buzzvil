package com.buzzvil.android.main

import android.graphics.Bitmap
import android.util.Log
import com.bumptech.glide.load.Encoder
import com.bumptech.glide.load.Option
import com.bumptech.glide.load.Options
import com.bumptech.glide.util.LogTime
import com.bumptech.glide.util.Util
import com.bumptech.glide.util.pool.GlideTrace
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

internal class BitmapEncoder : Encoder<Bitmap> {

    override fun encode(bitmap: Bitmap, file: File, options: Options): Boolean {
        val format = getFormat(bitmap, options)
        GlideTrace.beginSectionFormat(
            "encode: [%dx%d] %s", bitmap.width, bitmap.height, format
        )
        return try {
            val start = LogTime.getLogTime()
            val quality = options.get(COMPRESSION_QUALITY)!!
            var success = false
            var os: OutputStream? = null
            try {
                os = FileOutputStream(file)
                bitmap.compress(format, quality, os)
                os.close()
                success = true
            } catch (e: IOException) {
                if (Log.isLoggable(TAG, Log.DEBUG)) {
                    Log.d(TAG, "Failed to encode Bitmap", e)
                }
            } finally {
                if (os != null) {
                    try {
                        os.close()
                    } catch (e: IOException) {
                        // Do nothing.
                    }
                }
            }
            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                Log.v(
                    TAG,
                    "Compressed with type: "
                            + format
                            + " of size "
                            + Util.getBitmapByteSize(bitmap)
                            + " in "
                            + LogTime.getElapsedMillis(start)
                            + ", options format: "
                            + options.get(COMPRESSION_FORMAT)
                            + ", hasAlpha: "
                            + bitmap.hasAlpha()
                )
            }
            success
        } finally {
            GlideTrace.endSection()
        }
    }

    private fun getFormat(bitmap: Bitmap, options: Options): Bitmap.CompressFormat {
        val format = options.get(COMPRESSION_FORMAT)
        return format ?: if (bitmap.hasAlpha()) {
            Bitmap.CompressFormat.PNG
        } else {
            Bitmap.CompressFormat.JPEG
        }
    }

    companion object {
        /**
         * An integer option between 0 and 100 that is used as the compression quality.
         *
         *
         * Defaults to 90.
         */
        val COMPRESSION_QUALITY = Option.memory("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionQuality", 90)

        /**
         * An [android.graphics.Bitmap.CompressFormat] option used as the format to encode the
         * [android.graphics.Bitmap].
         *
         *
         * Defaults to [android.graphics.Bitmap.CompressFormat.JPEG] for images without alpha and
         * [android.graphics.Bitmap.CompressFormat.PNG] for images with alpha.
         */
        val COMPRESSION_FORMAT = Option.memory<Bitmap.CompressFormat>("com.bumptech.glide.load.resource.bitmap.BitmapEncoder.CompressionFormat")
        private const val TAG = "BitmapEncoder"
    }
}