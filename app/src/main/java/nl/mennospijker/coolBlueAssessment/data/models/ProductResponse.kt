package nl.mennospijker.coolBlueAssessment.data.models

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("products")
    val products: List<ProductItemResponse>,
    @SerializedName("totalResults")
    val totalResults: Int
)