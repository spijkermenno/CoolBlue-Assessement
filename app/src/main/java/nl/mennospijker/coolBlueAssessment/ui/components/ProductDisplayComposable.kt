package nl.mennospijker.coolblueassessment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import nl.mennospijker.coolblueassessment.domain.models.Product

@Composable
fun ProductDisplay(modifier: Modifier = Modifier, product: Product) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(Modifier.padding(8.dp)) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .background(Color.White)
                    .heightIn(max = 150.dp)
                    .aspectRatio(2 / 5f, true)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.width(16.dp))

            Column(Modifier.fillMaxSize(), Arrangement.SpaceBetween) {

                Text(product.name, fontWeight = FontWeight.Bold)

                Text("Waardering: ${product.reviewSummary.rating} (${product.reviewSummary.reviewCount} beoordelingen)")

                Text("€${product.price}")

                when (product.available) {
                    Product.Availability.AVAILABLE -> Text(
                        text = "Op voorraad",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF4CAF50)
                    )

                    Product.Availability.LIMITED -> Text(
                        text = "Beperkt op voorraad",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFF44336)
                    )

                    Product.Availability.OUT_OF_STOCK -> Text(
                        text = "Niet op voorraad",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFE91E63)
                    )

                    Product.Availability.UNKNOWN -> Unit
                }

                // I would also like to display the USPs, but there is no time :(
            }

        }
    }
}