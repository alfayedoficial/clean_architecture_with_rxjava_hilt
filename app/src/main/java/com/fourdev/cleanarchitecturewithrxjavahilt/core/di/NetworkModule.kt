package com.fourdev.cleanarchitecturewithrxjavahilt.core.di

import com.fourdev.cleanarchitecturewithrxjavahilt.BuildConfig
import com.fourdev.cleanarchitecturewithrxjavahilt.data.api.FourSquareApi
import com.fourdev.cleanarchitecturewithrxjavahilt.data.interceptor.AuthInterceptor
import com.fourdev.cleanarchitecturewithrxjavahilt.utils.Constants.BASE_URL
import com.fourdev.cleanarchitecturewithrxjavahilt.utils.Constants.TIME_OUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/23/2022 - 12:27 AM
 */
@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    fun providesAuthInterceptor()= AuthInterceptor()

    @Singleton
    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) =
       Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create(FourSquareApi::class.java)
}