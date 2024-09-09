package pe.com.albert.features.app_rating.presentation.rating

sealed class RatingNavigation {
    data object GoToListApps : RatingNavigation()
}