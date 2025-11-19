package com.example.stuffies_proyect_grupo_6.repository

import com.example.stuffies_proyect_grupo_6.model.Post
import com.example.stuffies_proyect_grupo_6.remote.PostRetrofitInstance

/**
 * Repositorio que obtiene los productos desde el microservicio Spring Boot.
 */
class PostRepository {

    suspend fun getPosts(): List<Post> {
        return PostRetrofitInstance.api.getProductos()
    }
}
