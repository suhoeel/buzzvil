package com.buzzvil.campaign.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApisInterceptor @Inject constructor() : Interceptor {

    companion object {
        const val TAG = "OkHttpInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url.toString()

        val request = original.newBuilder()
            .header("Content-Type", "application/json")
            .build()

        val response = chain.proceed(request)
        Log.d(TAG, "request method ${original.method}")
        Log.d(TAG, "request headers ${original.headers}")
        Log.d(TAG, "connection ${chain.connection()}")
        Log.d(TAG, "request requested url ${response.request.url}")
        Log.d(TAG, "request body ${response.peekBody(2048).string()}")
        return response
    }

}