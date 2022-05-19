package com.example.core.di

import android.content.Context
import androidx.room.Room
import com.example.core.api.NewsApi
import com.example.core.api.RetrofitInstance
import com.example.core.db.ArticleDao
import com.example.core.db.ArticleDatabase
import com.example.core.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        retrofitInstance: RetrofitInstance
    ): NewsApi {
        return retrofitInstance.buildApi(NewsApi::class.java)
    }

    @Provides
    fun providesUserDao(articleDatabase: ArticleDatabase): ArticleDao =
        articleDatabase.getArticleDao()


    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): ArticleDatabase =
        Room.databaseBuilder(context, ArticleDatabase::class.java, "article_db").build()

    @Provides
    fun providesUserRepository(api: NewsApi, articleDao: ArticleDao): NewsRepository =
        NewsRepository(api, articleDao)

}