package nl.mennospijker.coolblueassessment.data

import nl.mennospijker.coolblueassessment.domain.ProductRepository
import nl.mennospijker.coolblueassessment.domain.models.Product

class ProductRepositoryImpl(private val api: CoolblueApi) : ProductRepository {
    override suspend fun performSearch(query: String): Result<List<Product>> {
        return try {
            val response = api.getProducts(query)
            val domainProducts = response.products.map { it.toDomain() }

            // Since the API is mocked we perform the actual search matching in here.
            return if (query.isNotEmpty()) {
                val filteredResults =
                    domainProducts.filter { it.name.contains(query, ignoreCase = true) }
                Result.success(filteredResults)
            } else {
                Result.success(domainProducts)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}