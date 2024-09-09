package pe.com.albert.features.app_rating.presentation.list_application

import pe.com.albert.features.app_rating.domain.model.Application

data class ApplicationListUiState(
    val isLoading: Boolean = false,
    val applications: List<Application> = emptyList(),
    val error: String = ""
)