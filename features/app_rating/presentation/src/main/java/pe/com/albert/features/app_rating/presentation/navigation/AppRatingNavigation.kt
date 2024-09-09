package pe.com.albert.features.app_rating.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import pe.com.albert.features.app_rating.presentation.form.FormScreen
import pe.com.albert.features.app_rating.presentation.list_application.ApplicationListScreen
import pe.com.albert.features.app_rating.presentation.navigation.AppRatingNavigation.APP_RATING_SCREEN
import pe.com.albert.features.app_rating.presentation.navigation.AppRatingNavigation.FORM_SCREEN
import pe.com.albert.features.app_rating.presentation.navigation.AppRatingNavigation.LOCAL_ROUTE
import pe.com.albert.features.app_rating.presentation.navigation.AppRatingNavigation.RATING_APP_SCREEN
import pe.com.albert.features.app_rating.presentation.rating.RatingAppScreen

object AppRatingNavigation {
    const val LOCAL_ROUTE = "/app_rating_navigation"
    const val APP_RATING_SCREEN: String = "app_rating_screen"
    const val FORM_SCREEN: String = "form_screen"
    const val RATING_APP_SCREEN: String = "rating_app_screen"
}

sealed class AppRatingRoutes(val route: String) {
    data object AppRating : AppRatingRoutes(APP_RATING_SCREEN)
    data object Form :
        AppRatingRoutes("$FORM_SCREEN/{${NavArgs.IdApplication.key}}/{${NavArgs.NameApplication.key}}") {
        fun route(idApplication: String, nameApplication: String): String {
            return "$FORM_SCREEN/$idApplication/$nameApplication"
        }
    }

    data object RatingApp : AppRatingRoutes("${RATING_APP_SCREEN}/{${NavArgs.IdApplication.key}}/{${NavArgs.NameApplication.key}}") {
        fun route(idApplication: String, nameApplication: String): String {
            return "$RATING_APP_SCREEN/$idApplication/$nameApplication"
        }
    }
}

private enum class NavArgs(val key: String) {
    IdApplication("idApplication"),
    NameApplication("nameApplication")
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
                goToForm = { id, name ->
                    navController.navigate(AppRatingRoutes.Form.route(id, name))
                }
            )
        }

        composable(
            route = AppRatingRoutes.Form.route,
            arguments = listOf(navArgument(NavArgs.IdApplication.key) { type = NavType.StringType })
        ) { backStackEntry ->
            val idApplication = backStackEntry.arguments?.getString(NavArgs.IdApplication.key)
            val nameApplication =
                backStackEntry.arguments?.getString(NavArgs.NameApplication.key) ?: ""
            idApplication?.let {
                FormScreen(it, nameApplication, goToRating = {idApp,name->
                    navController.navigate(AppRatingRoutes.RatingApp.route(idApp,name))
                }) {
                    navController.popBackStack()
                }
            }
        }

        composable(
            route = AppRatingRoutes.RatingApp.route,
            arguments = listOf(navArgument(NavArgs.IdApplication.key) { type = NavType.StringType })
        ) { backStackEntry ->
            val idApplication = backStackEntry.arguments?.getString(NavArgs.IdApplication.key)
            val nameApplication =
                backStackEntry.arguments?.getString(NavArgs.NameApplication.key) ?: ""
            idApplication?.let {
                RatingAppScreen(it,nameApplication){
                    navController.popBackStack()
                }
            }
        }

    }
}