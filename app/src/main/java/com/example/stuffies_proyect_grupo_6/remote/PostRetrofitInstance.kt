package com.example.stuffies_proyect_grupo_6.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Configuración de Retrofit para consumir el microservicio de Spring Boot.
 *
 * IMPORTANTE:
 * - Emulador Android Studio -> usar 10.0.2.2 (equivale a localhost del PC).
 * - Spring Boot debe estar corriendo en el puerto 8080 del PC.
 *
 * URL final que va a llamar la app:
 *   http://10.0.2.2:8080/api/productos
 */
object PostRetrofitInstance {

    // ▶ EMULADOR (Android Studio en el mismo PC que el backend)
    private const val BASE_URL_EMULADOR = "http://10.0.2.2:8080/"

    // ▶ CELULAR REAL en la misma red que el PC
    //    Cambia "192.168.X.X" por la IP real de tu PC si pruebas con APK en el celular.
    private const val BASE_URL_CELULAR = "http://192.168.0.10:8080/"

    // 👇 ELIGE UNA: para emulador o para celular real
    // Para emulador:
    private const val BASE_URL = BASE_URL_EMULADOR

    // Si algún día pruebas en celular real, comenta la línea de arriba y descomenta esta:
    // private const val BASE_URL = BASE_URL_CELULAR

    // Cliente HTTP con timeouts básicos
    private val httpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .build()

    // Instancia de Retrofit que usará PostApiService
    val api: PostApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApiService::class.java)
    }
}
