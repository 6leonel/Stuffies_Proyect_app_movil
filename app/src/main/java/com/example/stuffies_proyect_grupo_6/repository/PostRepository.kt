package com.example.stuffies_proyect_grupo_6.repository

import com.example.stuffies_proyect_grupo_6.data.model.Post
import com.example.stuffies_proyect_grupo_6.data.remote.PostRetrofitInstance

class PostRepository {
    suspend fun getPosts(): List<Post> = PostRetrofitInstance.api.getPosts()
}
