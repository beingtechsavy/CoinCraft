package com.example.coincraft
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException

class CoinRepository {

    private val client = OkHttpClient()
    private val url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd"

    fun fetchCoins(): List<Coin>? {
        val request = Request.Builder()
            .url(url)
            .get()
            .addHeader("accept", "application/json")
            .build()

        return try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            val coinType = object : TypeToken<List<Coin>>() {}.type
            Gson().fromJson(json, coinType)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}