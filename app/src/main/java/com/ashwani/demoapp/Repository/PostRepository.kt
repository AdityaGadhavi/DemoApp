package com.ashwani.demoapp.Repository

import com.ashwani.demoapp.Model.ResponseModel
import com.ashwani.demoapp.Network.RetrofitBuilder
import retrofit2.Response

class PostRepository  {

    suspend fun getPost(page: Int, size: Int): Response<ResponseModel> = RetrofitBuilder.api.getAllPost(page = page, size = size)

}