package nl.mennospijker.coolblueassessment.ui

import nl.mennospijker.coolblueassessment.domain.models.Product

sealed class ProductUiState {
    object Empty : ProductUiState()
    object Loading : ProductUiState()
    class Success(val products: List<Product>) : ProductUiState()
    class Error(val message: String) : ProductUiState()
}