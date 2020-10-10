package com.techdoctorbd.networkcallwithretrofit.interfaces

import com.techdoctorbd.networkcallwithretrofit.models.ServerResponse
import com.techdoctorbd.networkcallwithretrofit.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiInterface {
    @POST("/retrofit_get_post/server_side_code.php")
    fun getUserValidity(@Body userLoginCredential: User?): Call<ServerResponse?>?

    @GET("/retrofit_get_post/server_side_code.php")
    fun getJoke(@Query("user_id") userId: String?): Call<ServerResponse?>?
}