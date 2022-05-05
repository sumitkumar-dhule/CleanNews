package com.example.cleannews.repository

import com.example.cleannews.api.RetrofitInstance
import com.example.cleannews.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

}