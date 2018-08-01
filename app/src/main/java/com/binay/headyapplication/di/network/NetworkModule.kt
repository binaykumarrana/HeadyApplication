package com.binay.headyapplication.di.network

import com.binay.headyapplication.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by binay on 02/08/18.
 */
@Module
class NetworkModule {
    var url: String

    constructor(url: String) {
        this.url = url
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        var okHttpBuilder = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                    .build()
            val requestBuilder = original.newBuilder()
                    .url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        })
        return okHttpBuilder.build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(url)
                .build()
    }

    @Provides
    @Singleton
    fun provideTaxiApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}