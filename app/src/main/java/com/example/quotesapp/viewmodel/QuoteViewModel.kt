package com.example.quotesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.domin.model.QuoteModel
import com.example.quotesapp.domin.repository.QuoteRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository:QuoteRepo): ViewModel(){

    private var _state= MutableStateFlow(emptyList<QuoteModel>())
    val state:StateFlow<List<QuoteModel>>
        get() = _state

    init {
        viewModelScope.launch {
            val list=repository.getQuote()
            _state.value=list
        }
    }


}