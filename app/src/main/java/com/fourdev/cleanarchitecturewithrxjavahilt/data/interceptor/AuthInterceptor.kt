package com.fourdev.cleanarchitecturewithrxjavahilt.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 5/23/2022 - 12:00 AM
 */
class AuthInterceptor : Interceptor{


    override fun intercept(chain: Interceptor.Chain): Response {
       val request = chain.request()
        val originalHttpUrl = request.url()
        originalHttpUrl.newBuilder()
//            .addQueryParameter(CLIENT_ID, clientId)
//            .addQueryParameter(CLIENT_SECRET, clientSecret)
//            .addQueryParameter(VERSION, API_VERSION)
//            .addQueryParameter(QUERY, query_key)
            .build()

        val requestBuilder = request.newBuilder().url(originalHttpUrl).build()

        return chain.proceed(requestBuilder)
    }
}