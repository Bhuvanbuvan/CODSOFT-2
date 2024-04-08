package com.example.quotesapp.domin.repository

import com.example.quotesapp.domin.apiservice.ApiService
import com.example.quotesapp.domin.model.QuoteModel

class QuoteRepo(private val apiService: ApiService) {

    suspend fun getQuote():List<QuoteModel>{
        return apiService.getQuotes()
    }
}