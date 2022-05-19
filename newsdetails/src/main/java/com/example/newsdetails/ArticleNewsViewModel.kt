package com.example.newsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Article
import com.example.core.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArticleNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

}