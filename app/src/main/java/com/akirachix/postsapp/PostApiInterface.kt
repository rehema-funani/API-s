package com.akirachix.postsapp

import com.akirachix.postsapp.Comments
import com.akirachix.postsapp.Posts
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApiInterface {
    @GET("/posts")
    fun fetchPosts(): Call<List<Posts>>

    @GET("/posts/{postId}")
    fun fetchPostsById(@Path("postId") postId:Int): Call<Posts>

    @GET("/posts/{postId}/comments")
    fun fetchCommentsByPostId(@Path("postId") postId: Int): Call<List<Comments>>
}