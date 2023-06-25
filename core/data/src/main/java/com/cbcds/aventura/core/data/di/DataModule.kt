package com.cbcds.aventura.core.data.di

import android.content.Context
import com.cbcds.aventura.core.data.api.GoogleSignInClientFacade
import com.cbcds.aventura.core.data.api.OnboardingRepository
import com.cbcds.aventura.core.data.api.UserRepository
import com.cbcds.aventura.core.data.internal.DefaultGoogleSignInClientFacade
import com.cbcds.aventura.core.data.internal.DefaultOnboardingRepository
import com.cbcds.aventura.core.data.internal.DefaultUserRepository
import com.cbcds.aventura.core.network.api.UserApi
import com.cbcds.aventura.core.network.internal.user.FirebaseUserApi
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindsOnboardingRepository(
        onboardingRepository: DefaultOnboardingRepository
    ): OnboardingRepository

    @Binds
    @Singleton
    abstract fun bindsUserRepository(userRepository: DefaultUserRepository): UserRepository

    @Binds
    @Singleton
    abstract fun bindsGoogleUserRepository(
        googleUserRepository: DefaultGoogleSignInClientFacade
    ): GoogleSignInClientFacade

    @Binds
    @Singleton
    abstract fun bindsUserApi(userApi: FirebaseUserApi): UserApi

    companion object {

        @Provides
        @Singleton
        fun providesGoogleSignInClient(@ApplicationContext context: Context): SignInClient {
            return Identity.getSignInClient(context)
        }
    }
}
