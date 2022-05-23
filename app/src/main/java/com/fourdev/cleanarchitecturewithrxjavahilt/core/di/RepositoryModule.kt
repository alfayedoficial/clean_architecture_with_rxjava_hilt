package com.fourdev.cleanarchitecturewithrxjavahilt.core.di

import com.fourdev.cleanarchitecturewithrxjavahilt.data.api.FourSquareApi
import com.fourdev.cleanarchitecturewithrxjavahilt.data.repo.RestaurantRepoImpl
import com.fourdev.cleanarchitecturewithrxjavahilt.domain.repo.RestaurantRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/23/2022 - 12:23 AM
 */
@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRestaurantRepository(api : FourSquareApi): RestaurantRepo {
        return RestaurantRepoImpl(api)
    }
}