package com.example.core.api

import com.example.core.util.Constants.Companion.BASE_URL
import com.example.core.util.Util.getOkHttpClientBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitInstance @Inject constructor() {

    fun <Api> buildApi(api: Class<Api>): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClientBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}