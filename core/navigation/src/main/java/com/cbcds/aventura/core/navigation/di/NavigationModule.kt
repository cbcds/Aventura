package com.cbcds.aventura.core.navigation.di

import com.cbcds.aventura.core.navigation.DefaultNavigationController
import com.cbcds.aventura.core.navigation.NavigationController
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
    fun bindsNavigationController(
        navigationController: DefaultNavigationController,
    ): NavigationController
}