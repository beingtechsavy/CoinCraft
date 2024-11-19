package com.example.coincraft


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter


@Composable
fun LandingPage(
    modifier: Modifier = Modifier,
    viewModel: CoinViewModel ,
    navigatetoDetail: (Coin) -> Unit
) {
    val coins = viewModel.coins.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(18, 64, 25))
    ) {
        Column(Modifier.fillMaxSize()) {
            Text(
                "CoinCraft",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 32.dp),
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

            if (coins.value.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),

                    modifier = Modifier.fillMaxSize()
                ) {
                    items(coins.value.size) { index ->
                        CoinItem(coin = coins.value[index], navigatetoDetail)
                    }
                }
            }
        }
    }

}

@Composable
fun CoinItem(coin: Coin,navigatetoDetail: (Coin) -> Unit) {


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable {
            navigatetoDetail(coin)
        }
            .border(
                border = BorderStroke(2.dp, Color(0xFF365BC9)),
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = coin.image, error = painterResource(
                    androidx.core.R.drawable.ic_call_decline
                )
            ),
            contentDescription = coin.image,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1F),

            )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = coin.symbol,
                color = Color.White,
                style = TextStyle(
                    fontWeight = FontWeight(500),
                    fontSize = 24.sp
                ),
                modifier = Modifier.padding(top = 5.dp, bottom = 4.dp)
            )
            Text(
                text = coin.name,
                color = Color.White,
                maxLines = 1,
                style = TextStyle(fontWeight = FontWeight(500)),
                modifier = Modifier.padding(top = 5.dp, bottom = 4.dp)
            )

        }
        CoinPriceDisplay(coin.current_price)

        PriceChange(
            modifier = Modifier.padding(
                top = 5.dp,
                bottom = 15.dp
            ), coin.price_change_percentage_24h
        )

    }
}


@Composable
fun CoinPriceDisplay(
    price: Double,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFE0E0E0),
    textColor: Color = Color.Black,
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Text(
            text = "$${String.format("%.2f", price)}",
            color = textColor,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
fun PriceChange(
    modifier: Modifier = Modifier,
    priceChange: Double
) {
    val color = if (priceChange > 0) Color(0xFF4CAF50) else Color(0xFFF44336)
    val icon = if (priceChange > 0) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown

    Box(
        modifier = modifier
            .background(color = color, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${String.format("%.2f", priceChange)}%",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}





