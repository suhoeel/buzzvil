package com.buzzvil.campaign.di

import com.buzzvil.campaign.data.source.CampaignsRepository
import com.buzzvil.campaign.data.source.CampaignsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBuilder {

    @Singleton
    @Binds
    abstract fun bindCampaignsRepository(campaignsRepositoryImp: CampaignsRepositoryImp): CampaignsRepository

}