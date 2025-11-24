package com.example.stuffies_proyect_grupo_6.repository

import com.example.stuffies_proyect_grupo_6.model.Post
import com.example.stuffies_proyect_grupo_6.remote.PostRetrofitInstance
import com.example.stuffies_proyect_grupo_6.data.remote.PostApiService

/**
 * Repositorio que obtiene los productos desde el microservicio Spring Boot.
 */
class PostRepository {

    private val api: PostApiService = PostRetrofitInstance.api

    suspend fun getPosts(): List<Post> {
        return api.getPosts()
    }
}
