package com.buzzvil.core.di

import com.buzzvil.core.repository.CampaignsRepository
import com.buzzvil.core.repository.CampaignsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBuilder {



    @Singleton
    @Binds
    abstract fun bindCampaignsRepository(campaignsRepositoryImp: CampaignsRepositoryImp): CampaignsRepository

}