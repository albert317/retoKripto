package pe.com.albert.features.app_rating.presentation.rating

sealed class RatingUiIntent {
    data class LoadData(val isApplication: String) : RatingUiIntent()
}