package com.example.stuffies_proyect_grupo_6.data.remote

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Respuesta de https://api.adviceslip.com/advice
data class AdviceWrapper(
    @SerializedName("slip") val slip: AdviceSlip
)

data class AdviceSlip(
    @SerializedName("id") val id: Int,
    @SerializedName("advice") val advice: String
)

interface AdviceService {
    @GET("advice")
    suspend fun getAdvice(): AdviceWrapper
}

/**
 * Cliente simple de Retrofit para la API externa.
 * Se deja como objeto singleton para que sea fácil de usar desde Compose o ViewModel.
 */
object AdviceApi {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.adviceslip.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: AdviceService = retrofit.create(AdviceService::class.java)
}
