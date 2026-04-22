package nl.mennospijker.coolblueassessment.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query, onValueChange = {
                query = it
                viewModel.performSearch(query)
            },
            label = {
                Text("Hier kun je zoeken!")
            }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            when (val state = uiState.value) {
                ProductUiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )

                is ProductUiState.Error -> {
                    Text("Er is een fout opgetreden! ${state.message}")
                }

                is ProductUiState.Success -> {
                    if (state.products.isEmpty()) {
                        Box(Modifier.fillMaxSize(), Alignment.Center) {
                            Text("Geen producten gevonden")
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(bottom = 16.dp)
                        ) {
                            item {
                                Text(
                                    "${state.products.count()} producten gevonden",
                                    Modifier.padding(8.dp)
                                )
                            }

                            items(state.products, key = { it.id }) { product ->
                                ProductDisplay(product = product)
                            }
                        }
                    }
                }

                ProductUiState.Empty -> {
                    Box(Modifier.fillMaxSize(), Alignment.Center) {
                        Text("Zoekresultaten worden weergegeven tijdens het typen...")
                    }
                }
            }
        }
    }
}