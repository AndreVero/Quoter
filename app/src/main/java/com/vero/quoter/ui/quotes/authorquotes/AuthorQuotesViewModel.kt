package com.vero.quoter.ui.quotes.authorquotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.quoter.data.db.entity.Quote
import com.vero.quoter.data.network.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorQuotesViewModel(private val quotesRepository: QuoteRepository): ViewModel() {

    val quotes = MutableLiveData<List<Quote>>()

    fun getQuotes() {
        viewModelScope.launch(Dispatchers.Default) {
            val quotesTmp = quotesRepository.getQuotes()
            quotes.postValue(quotesTmp)
        }
    }

    fun onLikeClick(quote: Quote) {
        viewModelScope.launch(Dispatchers.Default) {
            quote.liked = !quote.liked
            quotesRepository.setQuote(quote)
        }
        getQuotes()
    }
}