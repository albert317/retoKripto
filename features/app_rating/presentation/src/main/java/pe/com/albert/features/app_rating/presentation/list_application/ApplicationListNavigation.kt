package pe.com.albert.features.app_rating.presentation.list_application

sealed class ApplicationListNavigation {
    data class GoToForm(val id: String) : ApplicationListNavigation()
}