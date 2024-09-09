package pe.com.albert.features.app_rating.presentation.list_application

sealed class ApplicationListUiIntent {
    data object LoadData : ApplicationListUiIntent()
    data class GoToForm(val idApplication:String) : ApplicationListUiIntent()
}