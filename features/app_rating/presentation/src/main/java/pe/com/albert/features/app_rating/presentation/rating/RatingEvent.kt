package pe.com.albert.features.app_rating.presentation.rating

sealed class RatingEvent {
    data class ShowAlert(val error: String) : RatingEvent()
}