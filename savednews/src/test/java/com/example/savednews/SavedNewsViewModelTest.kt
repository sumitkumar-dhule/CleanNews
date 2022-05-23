package com.example.savednews

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.model.Article
import com.example.core.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

@RunWith(MockitoJUnitRunner::class)
class SavedNewsViewModelTest {

    private lateinit var viewModel: SavedNewsViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var article: Article

    @Mock
    lateinit var newsRepository: NewsRepository


    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = SavedNewsViewModel(newsRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun getSaveNewsTest() {
        val listOfArticles: LiveData<List<Article>> = MutableLiveData(listOf<Article>(article))
        whenever(newsRepository.getSavedNews()).thenReturn(listOfArticles)
        assertNotNull(viewModel.getSaveNews())
        assertEquals(1, viewModel.getSaveNews().value?.size)
        verify(newsRepository, times(2)).getSavedNews()
    }


    @Test
    fun saveArticleTest() {
        runBlocking {
            viewModel.saveArticle(article)

            launch(Dispatchers.Main) {
                verify(newsRepository).upsert(article)
            }
        }
    }

    @Test
    fun deleteArticleTest() {
        runBlocking {
            viewModel.deleteArticle(article)

            launch(Dispatchers.Main) {
                verify(newsRepository).deleteArticle(article)
            }
        }
    }

}