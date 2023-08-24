package com.giftech.terbit.ui.components.molecules

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.giftech.terbit.R
import com.giftech.terbit.ui.theme.TerbitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FfqFoodCategoryItem(
    title: String,
    @DrawableRes image: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FfqFoodCategoryItemPreview() {
    TerbitTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            FfqFoodCategoryItem(
                title = "Makanan pokok",
                image = R.drawable.img_staple_ffq_480_370,
                onClick = {},
            )
        }
    }
}