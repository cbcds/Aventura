package com.cbcds.aventura.core.data.di

import com.cbcds.aventura.core.data.api.UserRepository
import com.cbcds.aventura.core.data.internal.DefaultOnboardingRepository
import com.cbcds.aventura.core.data.api.OnboardingRepository
import com.cbcds.aventura.core.data.internal.DefaultUserRepository
import com.cbcds.aventura.core.network.api.UserApi
import com.cbcds.aventura.core.network.internal.user.FirebaseUserApi
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

    @Binds
    @Singleton
    fun bindsUserRepository(
        userRepository: DefaultUserRepository
    ): UserRepository

    @Binds
    @Singleton
    fun bindsUserApi(
        userApi: FirebaseUserApi
    ): UserApi
}