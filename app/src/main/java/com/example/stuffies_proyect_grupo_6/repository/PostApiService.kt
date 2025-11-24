package com.example.stuffies_proyect_grupo_6.remote

import com.example.stuffies_proyect_grupo_6.model.Post
import retrofit2.http.GET

/**
 * Servicio Retrofit para consumir el microservicio de Spring Boot.
 *
 * La URL completa que llamará el emulador es:
 *   http://10.0.2.2:8080/api/productos
 */
interface PostApiService {

    @GET("api/productos")
    suspend fun getPosts(): List<Post>
}
