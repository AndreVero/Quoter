package com.vero.quoter.ui.quotes.likedquotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vero.quoter.data.network.repository.QuoteRepository

class LikedQuotesViewModelFactory(private val quotesRepository: QuoteRepository) :
    ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LikedQuotesViewModel(quotesRepository) as T
    }
}