package nl.mennospijker.coolblueassessment.data

import nl.mennospijker.coolblueassessment.data.models.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CoolblueApi {
    @GET("mobile-assignment/search/")
    suspend fun getProducts(@Query("query") query: String): ProductResponse
}