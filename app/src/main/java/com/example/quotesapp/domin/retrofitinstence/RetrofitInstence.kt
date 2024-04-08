package com.example.quotesapp.domin.retrofitinstence


import com.example.quotesapp.domin.apiservice.ApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstence {
    fun apiProvide(builder: Retrofit.Builder):ApiService{
     return builder.build()
         .create(ApiService::class.java)
    }

    fun retrofitProvide():Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://type.fit/")
            .addConverterFactory(MoshiConverterFactory.create())
    }
}