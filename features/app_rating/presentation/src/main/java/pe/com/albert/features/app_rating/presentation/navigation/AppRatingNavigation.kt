package pe.com.albert.features.app_rating.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import pe.com.albert.features.app_rating.presentation.list_application.ApplicationListScreen
import pe.com.albert.features.app_rating.presentation.navigation.AppRatingNavigation.APP_RATING_SCREEN
import pe.com.albert.features.app_rating.presentation.navigation.AppRatingNavigation.LOCAL_ROUTE

object AppRatingNavigation {
    const val LOCAL_ROUTE = "/app_rating_navigation"
    const val APP_RATING_SCREEN: String = "app_rating_screen"
}

sealed class AppRatingRoutes(val route: String) {
    data object AppRating : AppRatingRoutes(APP_RATING_SCREEN)
}

fun NavGraphBuilder.appRatingNavGraph(
    navController: NavHostController,
    onNavigateToForm: () -> Unit
) {
    navigation(
        startDestination = AppRatingRoutes.AppRating.route,
        route = LOCAL_ROUTE
    ) {
        composable(route = AppRatingRoutes.AppRating.route) {
            ApplicationListScreen(
                goToForm = {
                    onNavigateToForm()
                }
            )
        }
    }
}