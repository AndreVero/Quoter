package com.vero.quoter.ui.quotes.userquotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vero.quoter.data.network.repository.QuoteRepository

class UserQuotesViewModelFactory(private val quotesRepository: QuoteRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserQuotesViewModel(quotesRepository) as T
    }
}