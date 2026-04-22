package nl.mennospijker.coolblueassessment.domain

import nl.mennospijker.coolblueassessment.domain.models.Product

interface ProductRepository {
    suspend fun performSearch(query: String): Result<List<Product>>
}