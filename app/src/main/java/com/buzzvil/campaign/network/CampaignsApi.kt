package com.buzzvil.campaign.network

import com.buzzvil.campaign.domain.response.AdsRes
import com.buzzvil.campaign.domain.response.ArticlesRes
import com.buzzvil.campaign.domain.response.ConfigRes
import com.buzzvil.campaign.domain.response.base.NetworkResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface CampaignsApi {

    @GET("test_config.json")
    suspend fun getConfig(): NetworkResponse<ConfigRes, ResponseBody>

    @GET("test_ads.json")
    suspend fun getAds(): NetworkResponse<AdsRes, ResponseBody>

    @GET("test_articles.json")
    suspend fun getArticles(): NetworkResponse<ArticlesRes, ResponseBody>

    @GET
    suspend fun downloadImage(@Url url: String): NetworkResponse<ResponseBody, ResponseBody>

}