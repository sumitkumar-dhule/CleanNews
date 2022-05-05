package com.example.cleannews.model

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)