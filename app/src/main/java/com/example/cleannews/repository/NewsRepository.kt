package com.example.cleannews.repository

import com.example.cleannews.api.NewsApi
import com.example.cleannews.api.RetrofitInstance
import com.example.cleannews.db.ArticleDao
import com.example.cleannews.db.ArticleDatabase
import com.example.cleannews.model.Article
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi, //AuthApi is the retrofit interface
    //val db: ArticleDatabase
    private val db: ArticleDao,
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews()


    suspend fun upsert(article: Article) = db.upsert(article)

    fun getSavedNews() = db.getAllArticles()

    suspend fun deleteArticle(article: Article) = db.deleteArticle(article)
}