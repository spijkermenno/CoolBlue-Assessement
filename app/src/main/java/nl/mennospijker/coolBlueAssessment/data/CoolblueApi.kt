package nl.mennospijker.coolBlueAssessment.data

import nl.mennospijker.coolBlueAssessment.data.models.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CoolblueApi {
    @GET("mobile-assignment/")
    suspend fun getProducts(@Query("query") query: String): ProductResponse
}