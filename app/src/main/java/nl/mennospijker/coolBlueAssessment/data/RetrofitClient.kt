package nl.mennospijker.coolBlueAssessment.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    const val BASE_URL = "https://bdk0sta2n0.execute-api.eu-west-1.amazonaws.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: CoolblueApi by lazy {
        retrofit.create(CoolblueApi::class.java)
    }
}