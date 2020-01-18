package com.example.restexample

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {
    @GET("/posts")
    fun getPosts(): Call<List<Post>>

    @POST("/posts")
    fun setPost(@Body post: Post): Call<Post>
}