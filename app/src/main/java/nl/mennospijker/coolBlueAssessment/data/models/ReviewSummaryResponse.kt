package nl.mennospijker.coolBlueAssessment.data.models

import com.google.gson.annotations.SerializedName

data class ReviewSummaryResponse(
    @SerializedName("reviewAverage")
    val rating: Double,
    @SerializedName("reviewCount")
    val reviewCount: Int
)