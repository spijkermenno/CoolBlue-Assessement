package nl.mennospijker.coolblueassessment.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import nl.mennospijker.coolblueassessment.domain.models.Product

@Composable
fun ProductDisplay(modifier: Modifier = Modifier, product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .heightIn(max = 80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Fit
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text(product.name)

                    Text("Rating: ${product.reviewSummary.rating} (${product.reviewSummary.reviewCount} reviews)")
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text("€${product.price}")

                    if (product.available == Product.Availability.AVAILABLE) {
                        Text(
                            text = "Op voorraad",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF3da333) // Groen
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
private fun ProductDisplayPreview() {
    ProductDisplay(product = Product.MOCK)
}