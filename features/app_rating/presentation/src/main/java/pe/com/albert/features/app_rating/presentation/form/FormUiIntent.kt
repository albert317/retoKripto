package pe.com.albert.features.app_rating.presentation.form

import pe.com.albert.features.app_rating.domain.model.Property

sealed class FormUiIntent {
    data class SaveProperty(val property: Property) : FormUiIntent()
}