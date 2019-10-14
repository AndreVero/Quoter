package com.vero.quoter.data.network.repository

import com.vero.quoter.data.db.entity.Quote

interface QuoteRepository {

    suspend fun getQuotes(): List<Quote>

    suspend fun setQuote(quote: Quote)

    suspend fun getLikedQuotes(): List<Quote>

    suspend fun getUserQuotes(): List<Quote>
}