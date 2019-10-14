package com.vero.quoter.ui.quotes.likedquotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.quoter.data.db.entity.Quote
import com.vero.quoter.data.network.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LikedQuotesViewModel(private val quotesRepository: QuoteRepository): ViewModel() {

    val quotes = MutableLiveData<List<Quote>>()

    fun getLikedQuotes() {
        viewModelScope.launch (Dispatchers.Default){
            val likedQuote = quotesRepository.getLikedQuotes()
            quotes.postValue(likedQuote)
        }
    }

    fun removeFromLiked(quote: Quote) {
        viewModelScope.launch(Dispatchers.Default) {
            quote.liked = false
            quotesRepository.setQuote(quote)
        }
        getLikedQuotes()
    }
}