package pe.com.albert.kripto

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import pe.com.albert.features.app_rating.presentation.navigation.AppRatingNavigation
import pe.com.albert.features.app_rating.presentation.navigation.appRatingNavGraph

@Composable
fun MyNavHost(
    navController: NavHostController,
) {
    val startDestination = AppRatingNavigation.LOCAL_ROUTE
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        appRatingNavGraph(navController = navController) {
            //navController.navigate()
        }
    }
}