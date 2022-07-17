package com.buzzvil.core.di

import android.content.Context
import androidx.room.Room
import com.buzzvil.core.database.BuzzvilDatabase
import com.buzzvil.core.database.CampaignsDao
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext appContext: Context): BuzzvilDatabase {
        return Room
            .databaseBuilder(appContext, BuzzvilDatabase::class.java, "buzzvil.db")
//            .addMigrations()
            .build()
    }

    @Provides
    @Reusable
    fun provideCampaignsDao(database: BuzzvilDatabase): CampaignsDao {
        return database.campaignsDao()
    }

}