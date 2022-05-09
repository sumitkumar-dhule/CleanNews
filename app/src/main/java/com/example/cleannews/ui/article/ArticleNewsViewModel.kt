package com.example.cleannews.ui.article

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Article
import com.example.core.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArticleNewsViewModel @Inject constructor(
    app: Application,
    private val newsRepository: NewsRepository
) : AndroidViewModel(app) {

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

}