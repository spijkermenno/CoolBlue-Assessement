package nl.mennospijker.coolblueassessment.data

import nl.mennospijker.coolblueassessment.data.models.ProductItemResponse
import nl.mennospijker.coolblueassessment.data.models.ReviewInformationResponse
import nl.mennospijker.coolblueassessment.data.models.ReviewSummaryResponse
import nl.mennospijker.coolblueassessment.domain.models.Product
import org.junit.Test
import kotlin.test.assertEquals

class ProductItemResponseToDomainTest {

    @Test
    fun `test ProductItemResponse to Product`() {
        // setup
        val response = ProductItemResponse(
            productId = 1,
            productName = "Test product",
            reviewInformation = ReviewInformationResponse(ReviewSummaryResponse(4.5, 100)),
            sellingPoints = listOf("usp1", "usp2"),
            availabilityState = 2,
            price = 2.0,
            imageUrl = "image URL",
            nextDayDelivery = true
        )

        // execute
        val result = response.toDomain()

        // assert
        assertEquals(response.productId, result.id)
        assertEquals(response.productName, result.name)
        assertEquals(response.reviewInformation.reviewSummary.rating, result.reviewSummary.rating)
        assertEquals(response.reviewInformation.reviewSummary.reviewCount, result.reviewSummary.reviewCount)
        assertEquals(response.price, result.price)
        assertEquals(Product.Availability.AVAILABLE, result.available)
        assertEquals(response.imageUrl, result.imageUrl)
    }
}