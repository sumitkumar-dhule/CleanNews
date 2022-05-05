package com.example.cleannews.di

import com.example.cleannews.api.NewsApi
import com.example.cleannews.util.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NewsApplicationModule {

    @Provides
    fun provideBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory {
        return KotlinJsonAdapterFactory()
    }

    @Provides
    fun provideCoroutineCallAdapterFactory(): CoroutineCallAdapterFactory {
        return CoroutineCallAdapterFactory()
    }

    @Provides
    fun provideMoshi(kotlinJsonAdapterFactory: KotlinJsonAdapterFactory): Moshi {
        return Moshi.Builder()
            .add(kotlinJsonAdapterFactory)
            .build()
    }

    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideRetrofit(baseUrl: String, moshiConverterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    fun provideRetrofitInstance(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }
}