package com.vero.quoter.ui.quotes.authorquotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vero.quoter.data.network.repository.QuoteRepository

class AuthorQuotesViewModelFactory(private val quoteRepository: QuoteRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthorQuotesViewModel(quoteRepository) as T
    }
}