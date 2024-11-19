package com.example.coincraft

import androidx.benchmark.perfetto.PerfettoConfig
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun Coindetail(modifier: Modifier = Modifier, coin: Coin) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color(18, 64, 25)
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = coin.name,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight(500)
        )
        Image(
            painter = rememberAsyncImagePainter(
                model = coin.image
            ),
            contentDescription = coin.symbol,
            modifier = modifier
                .wrapContentSize()
                .aspectRatio(1F),

            )


        Spacer(Modifier.height(24.dp))

        DescriptionBoxWithDetails(coin)

    }
}



@Composable
fun DescriptionBoxWithDetails(coin: Coin, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color(0x7A0B1528),
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .padding(16.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {



            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Symbol: ${coin.symbol}",
                    style = MaterialTheme.typography.displayMedium.copy(color = Color.LightGray)            ,
                    modifier= Modifier.wrapContentWidth(),
                    maxLines = 1
                )
                Text(
                    text = "Rank: #${coin.market_cap_rank}",
                    style = MaterialTheme.typography.displayMedium.copy(color = Color.LightGray)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Current Price:",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                CoinPriceDisplay(
                    price = coin.current_price,
                    backgroundColor = Color(0xFFE0F7FA),
                    textColor = Color.Black,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "24h Change:",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                PriceChange(
                    priceChange = coin.price_change_percentage_24h,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

        }
    }
}


