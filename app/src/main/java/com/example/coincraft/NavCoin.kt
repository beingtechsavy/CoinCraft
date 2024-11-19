package com.example.coincraft

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun navCoin(modifier: Modifier = Modifier, viewModel: CoinViewModel,navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.landingpage.route
    ) {
        composable(route = Screen.landingpage.route) {
            LandingPage(viewModel = viewModel, navigatetoDetail = {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "coin",
                    it
                )
                navController.navigate(Screen.DetailScreen.route)
            })
        }

        composable(route = Screen.DetailScreen.route) {

            val coin =
                navController.previousBackStackEntry?.savedStateHandle?.get<Coin>(
                    "coin"
                ) ?: Coin(" ", " ", " ", "",0.00,0,0,0.00)
            Coindetail(coin = coin)
        }

    }

}