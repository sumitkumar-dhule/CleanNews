package com.example.breakingnews


import android.app.Application
import com.example.core.repository.NewsRepository
import com.example.core.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BreakingNewsViewModelTest {
    private lateinit var breakingNewsViewModel: BreakingNewsViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var newsRepository: NewsRepository

    @Mock
    lateinit var application: Application

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        breakingNewsViewModel = BreakingNewsViewModel(application, newsRepository)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun getBrakingNews() {
        breakingNewsViewModel.getBrakingNews(Constants.COUNTRY_CODE)
    }
}