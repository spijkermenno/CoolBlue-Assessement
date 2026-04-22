package nl.mennospijker.coolblueassessment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nl.mennospijker.coolblueassessment.data.ProductRepositoryImpl
import nl.mennospijker.coolblueassessment.data.RetrofitClient
import nl.mennospijker.coolblueassessment.ui.ProductViewModel
import nl.mennospijker.coolblueassessment.ui.theme.CoolBlueAssessmentTheme
import nl.mennospijker.coolblueassessment.ui.SearchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // Because i had no time to implement Koin, we're using this for DI.
        val viewModel: ProductViewModel by viewModels {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val apiService = RetrofitClient.apiService
                    val repository = ProductRepositoryImpl(apiService)
                    return ProductViewModel(repository) as T
                }
            }
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoolBlueAssessmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SearchScreen(Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}
