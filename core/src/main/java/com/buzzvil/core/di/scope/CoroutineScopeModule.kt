package com.buzzvil.core.di.scope

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

@InstallIn(SingletonComponent::class)
@Module
object CoroutinesScopesModule {

    @Singleton // Provide always the same instance
    @ApplicationScope
    @Provides
    fun providesCoroutineScope(
        @IODispatcher coroutineDispatcher: CoroutineDispatcher
    ): CoroutineScope {
        // Run this code when providing an instance of CoroutineScope
        return CoroutineScope(SupervisorJob() + coroutineDispatcher)
    }
}