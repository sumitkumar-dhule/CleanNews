package com.example.cleannews.di

import android.content.Context
import androidx.room.Room
import com.example.cleannews.api.NewsApi
import com.example.cleannews.api.RetrofitInstance
import com.example.cleannews.db.ArticleDao
import com.example.cleannews.db.ArticleDatabase
import com.example.cleannews.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsAppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        retrofitInstance: RetrofitInstance
    ): NewsApi {
        return retrofitInstance.buildApi(NewsApi::class.java)
    }

    @Provides
    @Singleton //Singleton as it provides only single instance of database object
    fun providesDatabase(@ApplicationContext context: Context): ArticleDatabase =
        Room.databaseBuilder(context, ArticleDatabase::class.java, "article_db")
            .build()

    @Provides
    fun providesUserDao(articleDatabase: ArticleDatabase): ArticleDao =
        articleDatabase.getArticleDao()


    @Provides
    fun providesUserRepository(api: NewsApi, articleDao: ArticleDao): NewsRepository =
        NewsRepository(api, articleDao)

}