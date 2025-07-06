package com.htcmvvmcleanarchitecture.presentation.viewmodel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.htcmvvmcleanarchitecture.presentation.viewmodel.screen.ProductDetailsScreen
import com.htcmvvmcleanarchitecture.presentation.viewmodel.screen.ProductListScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.PRODUCT_LIST
    ) {
        composable(NavigationRoutes.PRODUCT_LIST) {
            ProductListScreen(onProductClick = { productId ->
                navController.navigate("${NavigationRoutes.PRODUCT_DETAILS}/$productId")
            })
        }

        composable(
            "${NavigationRoutes.PRODUCT_DETAILS}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailsScreen(productId = productId, navController = navController)
        }
    }
}