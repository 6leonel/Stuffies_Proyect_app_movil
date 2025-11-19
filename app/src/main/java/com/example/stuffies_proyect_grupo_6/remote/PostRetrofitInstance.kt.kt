package com.example.stuffies_proyect_grupo_6.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Cliente Retrofit para el microservicio de Spring Boot.
 *
 * - Emulador: http://10.0.2.2:8080/
 * - Dispositivo físico: IP local del PC (ej: http://192.168.1.10:8080/)
 */
object PostRetrofitInstance {

    private const val BASE_URL = "http://10.0.2.2:8080/"

    val api: PostApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApiService::class.java)
    }
}
