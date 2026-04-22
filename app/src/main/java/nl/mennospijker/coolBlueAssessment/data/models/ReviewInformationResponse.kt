package nl.mennospijker.coolblueassessment.data.models

import com.google.gson.annotations.SerializedName

data class ReviewInformationResponse(
    @SerializedName("reviewSummary")
    val reviewSummary: ReviewSummaryResponse
)