package com.example.stuffies_proyect_grupo_6.remote

import com.example.stuffies_proyect_grupo_6.data.remote.PostApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostRetrofitInstance {

    // 🟣 OPCIÓN 1: cuando pruebas en EMULADOR en el mismo PC
    // (microservicio Spring Boot corriendo en ese PC)
    private const val BASE_URL_EMULADOR = "http://10.0.2.2:8080/"

    // 🟢 OPCIÓN 2: cuando pruebas en CELULAR REAL conectado a la misma red
    // 10.199.5.213 es la IP del PC de la U donde corre el backend
    private const val BASE_URL_WIFI = "http://10.199.5.213:8080/"

    // 👇 Elige cuál usar comentando/descomentando:

    // Para probar en EMULADOR (Android Studio en el PC):
    private const val BASE_URL = BASE_URL_EMULADOR

    // Para generar APK y probar en CELULAR REAL, usa esta línea en vez de la de arriba:
    // private const val BASE_URL = BASE_URL_WIFI

    val api: PostApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApiService::class.java)
    }
}
