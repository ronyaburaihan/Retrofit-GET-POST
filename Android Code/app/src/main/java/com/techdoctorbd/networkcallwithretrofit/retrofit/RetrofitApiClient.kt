package com.techdoctorbd.networkcallwithretrofit.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitApiClient {
    private const val BASE_URL = "http://192.168.1.101"
    private val gson = GsonBuilder().setLenient().create()

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        if (retrofit == null) {
            synchronized(RetrofitApiClient::class.java) {
                if (retrofit == null) {
                    retrofit = Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                }
            }
        }

        return retrofit
    }

}