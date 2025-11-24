package com.example.stuffies_proyect_grupo_6.remote

import com.example.stuffies_proyect_grupo_6.data.remote.PostApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostRetrofitInstance {

    // 🟣 EMULADOR (Android Studio en el mismo PC que el backend)
    private const val BASE_URL_EMULADOR = "http://10.0.2.2:8080/"

    // 🟢 CELULAR REAL en la misma red que el PC de la U
    // 10.199.5.213 es la IP que me dijiste
    private const val BASE_URL_CELULAR = "http://10.199.5.213:8080/"

    // 👉 Elige UNA según dónde vas a probar:

    // Para emulador:
    private const val BASE_URL = BASE_URL_EMULADOR

    // Para celular real (cuando generes APK), cambia a:
    // private const val BASE_URL = BASE_URL_CELULAR

    val api: PostApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApiService::class.java)
    }
}
