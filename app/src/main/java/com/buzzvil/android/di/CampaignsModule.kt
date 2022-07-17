package com.buzzvil.android.di

import com.buzzvil.core.di.scope.DefaultDispatcher
import com.buzzvil.core.network.CampaignsApi
import com.buzzvil.core.repository.CampaignsRepository
import com.buzzvil.core.repository.GetCampaignsUseCase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
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
    fun provideGetCampaignsUseCase(campaignsRepository: CampaignsRepository, @DefaultDispatcher defaultDispatcher: CoroutineDispatcher): GetCampaignsUseCase {
        return GetCampaignsUseCase(campaignsRepository, defaultDispatcher)
    }

}