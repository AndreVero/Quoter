package com.vero.quoter.data.network.repository

import com.vero.quoter.data.db.dao.QuotesDao
import com.vero.quoter.data.db.entity.Quote

class QuoteRepositoryImpl(private val quoteDao: QuotesDao) : QuoteRepository {

    override suspend fun getUserQuotes(): List<Quote> {
        return quoteDao.getUserQuotes()
    }

    override suspend fun getLikedQuotes(): List<Quote> {
        return quoteDao.getLikedQuotes()
    }

    override suspend fun getQuotes(): List<Quote> {
        return quoteDao.getQuotes()
    }

    override suspend fun setQuote(quote: Quote) {
        quoteDao.insert(quote)
    }
}