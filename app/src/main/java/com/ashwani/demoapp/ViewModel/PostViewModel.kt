package com.ashwani.demoapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ashwani.demoapp.Model.ResponseModel
import com.ashwani.demoapp.Repository.PostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel(private val postRepository: PostRepository) : ViewModel() {
    val myResponse:MutableLiveData<Response<ResponseModel>> = MutableLiveData()
    fun getPost(page: Int, size: Int)
    {
        viewModelScope.launch{
           try{
               val response = postRepository.getPost(page,size)
                   myResponse.value = response

           }catch (e:Exception){
               Log.e("asdasdasda", "getPost: ${e.message}")
           }
        }
    }
}