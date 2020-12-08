package com.example.wizlan.services

import retrofit2.Call
import retrofit2.http.GET

interface transApi {
    @GET("PostModel")
    fun fetchAllPosts(): Call<List<PostModel>>
}