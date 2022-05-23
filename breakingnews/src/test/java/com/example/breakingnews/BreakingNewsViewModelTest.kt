package com.example.breakingnews


import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
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
import android.net.NetworkInfo
import com.example.core.model.NewsResponse
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class BreakingNewsViewModelTest {
    private lateinit var breakingNewsViewModel: BreakingNewsViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    lateinit var newsRepository: NewsRepository

    @Mock
    lateinit var application: Application

    @Mock
    lateinit var connectivityManager: ConnectivityManager

    @Mock
    lateinit var networkInfo: NetworkInfo

    @Mock
    lateinit var response: Response<NewsResponse>

    @Mock
    lateinit var newsResponse: NewsResponse


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
    fun getBrakingNewsWithWifiConnectivity() {

        runBlocking {
            whenever(application.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(
                connectivityManager
            )
            whenever(newsRepository.getBreakingNews(Constants.COUNTRY_CODE, 1)).thenReturn(response)
            whenever(response.isSuccessful).thenReturn(true)
            whenever(response.body()).thenReturn(newsResponse)

            whenever(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
            whenever(networkInfo.type).thenReturn(ConnectivityManager.TYPE_WIFI)

            breakingNewsViewModel.getBrakingNews(Constants.COUNTRY_CODE)

            launch(Dispatchers.Main) {
                verify(newsRepository).getBreakingNews(Constants.COUNTRY_CODE, 1)
            }
        }
    }

    @Test
    fun getBrakingNewsWithoutConnectivity() {

        runBlocking {
            whenever(application.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(
                connectivityManager
            )
            whenever(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
            whenever(networkInfo.type).thenReturn(ConnectivityManager.TYPE_VPN)

            breakingNewsViewModel.getBrakingNews(Constants.COUNTRY_CODE)

            launch(Dispatchers.Main) {
                verify(newsRepository, never()).getBreakingNews(Constants.COUNTRY_CODE, 1)
            }
        }
    }

}
