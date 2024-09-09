package pe.com.albert.features.app_rating.presentation.form

sealed class FormEvent {
    data class ShowAlert(val error: String) : FormEvent()
}