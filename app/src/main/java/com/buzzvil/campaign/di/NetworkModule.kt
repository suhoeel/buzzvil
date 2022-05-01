package com.buzzvil.campaign.di

import com.buzzvil.campaign.BuildConfig
import com.buzzvil.campaign.network.ApisInterceptor
import com.buzzvil.campaign.network.adapter.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApiInterceptor() : ApisInterceptor = ApisInterceptor()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: ApisInterceptor
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
//        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_SERVER_TEST)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(NetworkResponseAdapterFactory.create())
            .build()
    }

    /*@Provides
    @Reusable
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }*/

}