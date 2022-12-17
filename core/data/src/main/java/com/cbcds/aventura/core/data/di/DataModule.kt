package com.cbcds.aventura.core.data.di

import com.cbcds.aventura.core.data.repository.DefaultOnboardingRepository
import com.cbcds.aventura.core.data.repository.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindsOnboardingRepository(
        onboardingRepository: DefaultOnboardingRepository
    ): OnboardingRepository
}