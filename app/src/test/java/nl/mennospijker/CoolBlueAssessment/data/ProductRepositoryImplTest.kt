package nl.mennospijker.coolblueassessment.data

import android.util.Log
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import nl.mennospijker.coolblueassessment.data.models.ProductItemResponse
import nl.mennospijker.coolblueassessment.data.models.ProductResponse
import nl.mennospijker.coolblueassessment.domain.models.Product
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ProductRepositoryImplTest {
    @RelaxedMockK
    private lateinit var api: CoolblueApi

    private lateinit var repository: ProductRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = ProductRepositoryImpl(api)
    }

    @Test
    fun `performSearch returns success when query is Apple`() = runTest {
        coEvery { api.getProducts(any()) } returns ProductResponse(listOf(), 0)

        val result = repository.performSearch("Apple")

        assertTrue(result.isSuccess)
        assertTrue(result.getOrThrow().isEmpty())
    }

    @Test
    fun `performSearch returns success and result is filled`() = runTest {
        val mockedProduct: ProductItemResponse = mockk(relaxed = true) {
            every { productName } returns "Apple iPhone 15"
        }

        coEvery { api.getProducts(any()) } returns ProductResponse(listOf(mockedProduct), 1)

        val result = repository.performSearch("Apple")

        assertTrue(result.isSuccess)
        assertEquals(result.getOrThrow().first(), mockedProduct.toDomain())
    }

    @Test
    fun `performSearch returns success with empty query and result is filled`() = runTest {
        val mockedProduct: ProductItemResponse = mockk(relaxed = true)

        coEvery { api.getProducts(any()) } returns ProductResponse(listOf(mockedProduct), 1)

        val result = repository.performSearch("")

        assertTrue(result.isSuccess)
        assertEquals(result.getOrThrow().first(), mockedProduct.toDomain())
    }

    @Test
    fun `performSearch returns success with query and result is empty`() = runTest {
        val mockedProduct: ProductItemResponse = mockk(relaxed = true)

        coEvery { api.getProducts(any()) } returns ProductResponse(listOf(mockedProduct), 1)

        val result = repository.performSearch("Apple")

        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull(), emptyList())
    }

    @Test
    fun `performSearch returns error `() = runTest {
        val exceptionMessage = "Network error"

        coEvery { api.getProducts(any()) } throws Exception(exceptionMessage)

        val result = repository.performSearch("Apple")

        assertTrue(result.isFailure)
        assertEquals(exceptionMessage, result.exceptionOrNull()?.message)
    }
}