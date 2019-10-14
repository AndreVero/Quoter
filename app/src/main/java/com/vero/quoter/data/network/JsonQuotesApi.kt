package com.vero.quoter.data.network

import com.vero.quoter.data.db.entity.Quote
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "http://quotes.stormconsultancy.co.uk"

interface JsonQuotesApi {

    @GET("/quotes.json")
    suspend fun getQuotes(): Response<List<Quote>>

    companion object {
        fun getApi(): JsonQuotesApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(JsonQuotesApi::class.java)
    }
}