package com.example.stuffies_proyect_grupo_6.data.remote

import com.example.stuffies_proyect_grupo_6.model.Post
import retrofit2.http.GET

/**
 * Servicio Retrofit para consumir el microservicio Spring Boot.
 *
 * IMPORTANTE:
 * Cambia el path del @GET para que coincida con tu backend.
 * Si en el navegador pruebas con:
 *   http://localhost:8080/api/productos
 * usa @GET("api/productos")
 */
interface PostApiService {

    // ⬇️ Ajusta este path según tu controlador Spring Boot
    @GET("api/productos")
    suspend fun getPosts(): List<Post>
}
