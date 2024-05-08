package com.ashwani.demoapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashwani.demoapp.Adapter.ItemAdapter
import com.ashwani.demoapp.Model.ResponseModel
import com.ashwani.demoapp.Repository.PostRepository
import com.ashwani.demoapp.ViewModel.PostViewModel
import com.ashwani.demoapp.ViewModel.PostViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var postRepository: PostRepository
    private lateinit var postViewModelFactory: PostViewModelFactory
    private lateinit var postViewModel: PostViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var imgSearch: ImageView
    lateinit var toolbar: RelativeLayout
    lateinit var et_text: EditText
    private lateinit var itemAdapter: ItemAdapter
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var currentPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerV)
        imgSearch = findViewById(R.id.img_search)
        et_text = findViewById(R.id.et_text)
        toolbar = findViewById(R.id.toolbar)

        imgSearch.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                et_text.visibility = View.VISIBLE
            }
        })


        et_text.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                toolbar.visibility = View.GONE
            }
        })

//        initRecyclerView(it.body() as ArrayList<ResponseModel>)

        postRepository = PostRepository()
        postViewModelFactory = PostViewModelFactory(postRepository)
        postViewModel = ViewModelProvider(this, postViewModelFactory).get(PostViewModel::class.java)

        postViewModel.getPost(currentPage, 5)

        itemAdapter = ItemAdapter(ArrayList())

        val layoutManager = LinearLayoutManager(this)
        initRecyclerView(ArrayList(), layoutManager)

        postViewModel.myResponse.observe(this, Observer {
            Log.e("asdasdasda", "sdfdsfs   " + it.body()!!.data)
            itemAdapter.setPostData((it.body()!!.data))
            isLoading = false
            currentPage++
        })

        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                //you have to call loadmore items to get more data
                //                getMoreItems()
                Log.e("asdasdasda", "onScrolled   ")
                postViewModel.getPost(currentPage, 5)
            }
        })

    }

    private fun initRecyclerView(
        responseModels: ArrayList<ResponseModel>,
        layoutManager: LinearLayoutManager
    ) {
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = itemAdapter
    }
}
