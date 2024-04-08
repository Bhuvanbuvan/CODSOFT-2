package com.example.quotesapp.domin.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class QuoteModel(
    val text:String,
    val author:String
)
