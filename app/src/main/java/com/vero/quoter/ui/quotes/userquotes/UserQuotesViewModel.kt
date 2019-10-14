package com.vero.quoter.ui.quotes.userquotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vero.quoter.data.db.entity.Quote
import com.vero.quoter.data.network.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserQuotesViewModel(private val quoteRepository: QuoteRepository) :
    ViewModel() {

    val userQuotes = MutableLiveData<List<Quote>>()

    fun getUserQuotes() {
        viewModelScope.launch(Dispatchers.Default) {
            val quotesTmp = quoteRepository.getUserQuotes()
            userQuotes.postValue(quotesTmp)
        }
    }
}