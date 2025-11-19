package com.example.stuffies_proyect_grupo_6.data.remote

import com.example.stuffies_proyect_grupo_6.data.model.Post
import retrofit2.http.GET

interface PostApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}