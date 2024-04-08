package com.example.quotesapp.domin.apiservice


import com.example.quotesapp.domin.model.QuoteModel
import retrofit2.http.GET
interface ApiService {
    @GET("api/quotes")
    suspend fun getQuotes():List<QuoteModel>
}