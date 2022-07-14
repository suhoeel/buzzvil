package com.buzzvil.campaign.di

import com.buzzvil.campaign.data.source.CampaignsRepository
import com.buzzvil.campaign.domain.GetCampaignsUseCase
import com.buzzvil.campaign.network.CampaignsApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object CampaignsModule {

    @Provides
    @Reusable
    fun provideCampaignsApi(retrofit: Retrofit): CampaignsApi {
        return retrofit.create(CampaignsApi::class.java)
    }

    @Provides
    @Reusable
    fun provideGetCampaignsUseCase(campaignsRepository: CampaignsRepository): GetCampaignsUseCase {
        return GetCampaignsUseCase(campaignsRepository)
    }

}