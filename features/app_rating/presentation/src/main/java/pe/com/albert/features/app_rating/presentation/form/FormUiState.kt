package pe.com.albert.features.app_rating.presentation.form

import pe.com.albert.features.app_rating.domain.model.Property

data class FormUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val property: Property? = null
)