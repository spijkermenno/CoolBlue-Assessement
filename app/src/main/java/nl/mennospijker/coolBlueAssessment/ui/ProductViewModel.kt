package nl.mennospijker.coolblueassessment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nl.mennospijker.coolblueassessment.domain.ProductRepository

class ProductViewModel(
    private val productRepository: ProductRepository // Normally i would use Koin to inject this.
) : ViewModel() {
    private val _uiState: MutableStateFlow<ProductUiState> =
        MutableStateFlow(ProductUiState.Empty)
    val uiState = _uiState.asStateFlow()

    fun performSearch(query: String) {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading

            productRepository.performSearch(query)
                .onSuccess { _uiState.value = ProductUiState.Success(it) }
                .onFailure { _uiState.value = ProductUiState.Error(it.message ?: "Unknown error") }
        }
    }
}