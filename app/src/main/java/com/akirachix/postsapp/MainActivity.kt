package com.akirachix.postsapp

import Apiclient
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.postsapp.databinding.ActivityMainBinding
import com.example.postapp.PostsAdapter


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPosts()
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }

    fun fetchPosts(){
        val apiInterface = Apiclient.buildApiInterface(PostsApiInterface::class.java)
        val request = apiInterface.fetchPosts()
        request.enqueue(object  : Callback<List<Posts>> {
            override fun onResponse(p0: Call<List<Posts>>, p1: Response<List<Posts>>) {
                if (p1.isSuccessful){
                    val posts = p1.body()
                    if (posts != null){
                        displayPosts(posts)
                    }
//                    displayPosts(posts!!)
                    Toast.makeText(baseContext, "Fetched${posts!!.size}posts",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else{
                    Toast.makeText(baseContext, p1.errorBody()?.string(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(p0: Call<List<Posts>>, p1: Throwable) {
                Toast.makeText(baseContext, p1.message, Toast.LENGTH_LONG).show()
            }
        })

    }

    fun displayPosts(posts: List<Posts>){
        val postsAdapter = PostsAdapter(posts, this)
        binding.rvPosts.adapter = postsAdapter
    }

}