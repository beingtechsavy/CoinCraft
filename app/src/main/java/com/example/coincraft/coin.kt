package com.example.coincraft

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Coin(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: Double,
    val market_cap: Long,
    val market_cap_rank: Int,
    val price_change_percentage_24h: Double
) : Parcelable