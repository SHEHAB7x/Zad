package com.shehab.zad.ui.screens.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shehab.zad.ui.theme.ZadTheme

data class QuickAccessItem(
    val icon: String,
    val titleAr: String,
    val subtitle: String
)

@Composable
fun QuickAccessGrid(
    items: List<QuickAccessItem> = sampleQuickAccessItems,
    onItemClick: (QuickAccessItem) -> Unit = {}
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            maxItemsInEachRow = 2
        ) {
            items.forEach { item ->
                QuickAccessCard(
                    item = item,
                    onClick = {onItemClick(item)},
                    modifier = Modifier.fillMaxWidth(0.48f)
                )
            }
        }
    }
}

@Composable
fun QuickAccessCard(
    item: QuickAccessItem,
    onClick: () -> Unit = {},
    modifier: Modifier
){
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = item.icon,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = item.titleAr,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = item.subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

val sampleQuickAccessItems = listOf(
    QuickAccessItem("📖", "القرآن الكريم", "Last: Al-Baqarah 2:255"),
    QuickAccessItem("🧭", "اتجاه القبلة", "158° from north"),
    QuickAccessItem("📿", "الأذكار",       "Morning · Evening"),
    QuickAccessItem("📚", "التفسير",       "Ibn Kathir")
)

@Preview(showBackground = true)
@Composable
fun QuickAccessGridPreview(){
    ZadTheme {
        QuickAccessGrid()
    }
}