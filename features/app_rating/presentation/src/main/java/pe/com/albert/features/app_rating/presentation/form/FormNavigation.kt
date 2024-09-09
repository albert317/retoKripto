package pe.com.albert.features.app_rating.presentation.form

sealed class FormNavigation {
    data object GoToApplicationList : FormNavigation()
}