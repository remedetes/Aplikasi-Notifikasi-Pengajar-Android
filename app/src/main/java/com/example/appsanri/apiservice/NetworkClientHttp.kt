package com.example.appsanri.apiservice

import com.example.appsanri.model.RefData.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClientHttp {
    private lateinit var retrofit: Retrofit

    val client: Retrofit
        get() {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit
        }
}
