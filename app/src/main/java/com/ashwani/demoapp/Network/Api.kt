package com.ashwani.demoapp.Network


import com.ashwani.demoapp.Model.ResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api
{
    @GET("qfonapp-passenger")
    suspend fun getAllPost(@Query("page") page: Int, @Query("size") size: Int): Response<ResponseModel>
}