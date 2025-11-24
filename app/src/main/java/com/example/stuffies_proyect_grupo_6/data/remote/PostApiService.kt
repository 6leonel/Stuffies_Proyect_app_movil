package com.example.stuffies_proyect_grupo_6.data.remote

import com.example.stuffies_proyect_grupo_6.model.Post
import retrofit2.http.GET

/**
 * Servicio Retrofit para consumir el microservicio Spring Boot.
 *
 * Ajusta el path del @GET según tu backend
 * (por ejemplo "posts", "productos", "api/productos", etc.).
 */
interface PostApiService {

    @GET("posts")   // 🔧 CAMBIA SOLO ESTE STRING SI TU ENDPOINT ES OTRO
    suspend fun getPosts(): List<Post>
}
