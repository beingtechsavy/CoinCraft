package com.example.coincraft

sealed class Screen(val route:String) {
    object  landingpage:Screen("landingpage")
    object  DetailScreen: Screen("detailscreen")

}