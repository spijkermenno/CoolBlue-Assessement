package nl.mennospijker.coolblueassessment.data

import nl.mennospijker.coolblueassessment.data.models.ReviewSummaryResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class ReviewSummaryResponseToDomainTest {
    @Test
    fun `ReviewSummaryResponse to ReviewSummary`() {
        // setup
        val response = ReviewSummaryResponse(4.5, 100)

        // execute
        val result = response.toDomain()

        // assert
        assertEquals(result.reviewCount, response.reviewCount)
        assertEquals(result.rating, response.rating)
    }
}