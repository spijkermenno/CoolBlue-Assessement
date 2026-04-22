package nl.mennospijker.coolblueassessment.data

import nl.mennospijker.coolblueassessment.data.models.ProductItemResponse
import nl.mennospijker.coolblueassessment.data.models.ReviewSummaryResponse
import nl.mennospijker.coolblueassessment.domain.models.Product
import nl.mennospijker.coolblueassessment.domain.models.ReviewSummary

fun ProductItemResponse.toDomain(): Product {
    return Product(
        id = this.productId,
        name = this.productName,
        price = this.price,
        imageUrl = this.imageUrl,
        reviewSummary = this.reviewInformation.reviewSummary.toDomain(),
        available = Product.Availability.getAvailability(this.availabilityState)
    )
}

fun ReviewSummaryResponse.toDomain(): ReviewSummary {
    return ReviewSummary(
        rating = this.rating,
        reviewCount = this.reviewCount
    )
}