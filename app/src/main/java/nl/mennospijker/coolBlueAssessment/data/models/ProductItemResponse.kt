package nl.mennospijker.coolBlueAssessment.data.models

import com.google.gson.annotations.SerializedName

data class ProductItemResponse(
    @SerializedName("productId")
    val productId: Int,
    @SerializedName("productName")
    val productName: String,
    @SerializedName("reviewInformation")
    val reviewInformation: ReviewInformationResponse,
    @SerializedName("USPs")
    val sellingPoints: List<String>,
    @SerializedName("availabilityState")
    val availabilityState: Int,
    @SerializedName("salesPriceIncVat")
    val price: Double,
    @SerializedName("productImage")
    val imageUrl: String,
    @SerializedName("nextDayDelivery")
    val nextDayDelivery: Boolean
)