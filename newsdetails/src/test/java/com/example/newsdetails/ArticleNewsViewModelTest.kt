package com.example.newsdetails

import com.example.core.model.Article
import com.example.core.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class ArticleNewsViewModelTest {

    private lateinit var articleNewsViewModel: ArticleNewsViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var newsRepository: NewsRepository

    @Mock
    lateinit var article: Article

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        articleNewsViewModel = ArticleNewsViewModel(newsRepository)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun saveArticleTest() {
        runBlocking {
            articleNewsViewModel.saveArticle(article)

            launch(Dispatchers.Main) {
                verify(newsRepository).upsert(article)
            }
        }
    }
}