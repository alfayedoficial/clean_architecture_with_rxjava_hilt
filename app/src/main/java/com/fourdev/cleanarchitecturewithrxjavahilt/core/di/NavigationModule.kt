package com.fourdev.cleanarchitecturewithrxjavahilt.core.di

import com.fourdev.cleanarchitecturewithrxjavahilt.core.navigation.AppNavigator
import com.fourdev.cleanarchitecturewithrxjavahilt.core.navigation.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/22/2022 - 9:04 PM
 */

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {

    @Binds
    abstract fun bindAppNavigator(appNavigator: AppNavigatorImpl): AppNavigator
}