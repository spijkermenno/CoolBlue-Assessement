package nl.mennospijker.coolblueassessment.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.mennospijker.coolblueassessment.ui.components.ProductDisplay

@Composable
fun SearchScreen(modifier: Modifier = Modifier, viewModel: ProductViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    var query by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query, onValueChange = {
                query = it
                viewModel.performSearch(query)
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when (val state = uiState.value) {
                ProductUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )

                is ProductUiState.Error -> {
                    Text("Error! ${state.message}")
                    Log.d("TAG", "SearchScreen: ${state.message}")
                }
                is ProductUiState.Success -> {
                    if (state.products.isEmpty()) {
                        Text("No products found")
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(bottom = 16.dp)
                        ) {
                            items(state.products, key = { it.id }) { product ->
                                ProductDisplay(product = product)
                            }
                        }
                    }
                }

                ProductUiState.Empty -> Text("Start a search by entering a query.")
            }
        }
    }
}