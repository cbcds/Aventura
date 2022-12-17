package com.cbcds.aventura.core.navigation.di

import com.cbcds.aventura.core.navigation.AventuraNavigationManager
import com.cbcds.aventura.core.navigation.NavigationManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindsNavigationManager(
        navigationManager: AventuraNavigationManager
    ): NavigationManager
}