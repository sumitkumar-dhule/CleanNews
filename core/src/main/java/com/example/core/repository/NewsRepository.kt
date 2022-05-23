package com.example.core.repository

import com.example.core.api.NewsApi
import com.example.core.db.ArticleDao
import com.example.core.model.Article
import javax.inject.Inject

open class NewsRepository @Inject constructor(
    private val api: NewsApi,
    private val db: ArticleDao
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews()


    suspend fun upsert(article: Article) = db.upsert(article)

    fun getSavedNews() = db.getAllArticles()

    suspend fun deleteArticle(article: Article) = db.deleteArticle(article)
}