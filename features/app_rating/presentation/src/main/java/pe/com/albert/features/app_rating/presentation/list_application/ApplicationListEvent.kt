package pe.com.albert.features.app_rating.presentation.list_application

import pe.com.albert.features.app_rating.domain.util.Failure

sealed class ApplicationListEvent {
    data class ShowAlert(val error: Failure) : ApplicationListEvent()
}