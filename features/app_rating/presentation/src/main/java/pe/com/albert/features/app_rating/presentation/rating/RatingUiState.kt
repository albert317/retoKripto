package pe.com.albert.features.app_rating.presentation.rating

import pe.com.albert.features.app_rating.domain.model.Property

data class RatingUiState(
    val isLoading: Boolean = false,
    val property: Property?=null,
    val error: String = ""
)