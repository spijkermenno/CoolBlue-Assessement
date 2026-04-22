package nl.mennospijker.coolblueassessment.domain.models

import nl.mennospijker.coolblueassessment.domain.models.Product.Availability.AVAILABLE

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val reviewSummary: ReviewSummary,
    val available: Availability
) {

    enum class Availability {
        AVAILABLE,
        LIMITED,
        OUT_OF_STOCK,
        UNKNOWN;

        companion object {
            fun getAvailability(raw: Int): Availability {
                return when (raw) {
                    1 -> OUT_OF_STOCK
                    2 -> AVAILABLE // Assuming since nextDayDelivery is true for most 2
                    3 -> LIMITED // Only for iPhone 6s so assuming limited or refurbished
                    else -> UNKNOWN
                }
            }
        }
    }

    companion object {
        val MOCK = Product(
            id = 1,
            name = "mock",
            price = 2.0,
            imageUrl = "https://image.coolblue.nl/300x750/products/984921",
            reviewSummary = ReviewSummary(rating = 2.0, reviewCount = 1),
            available = AVAILABLE
        )
    }
}
