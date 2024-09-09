package pe.com.albert.features.app_rating.presentation.list_application

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.com.albert.features.app_rating.domain.usecase.GetApplicationsUseCase
import pe.com.albert.features.app_rating.domain.util.Result
import pe.com.albert.features.app_rating.presentation.util.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ApplicationListViewModel @Inject constructor(
    private val getApplicationsUseCase: GetApplicationsUseCase
) : BaseViewModel<ApplicationListUiState, ApplicationListEvent, ApplicationListUiIntent, ApplicationListNavigation>() {
    override fun createInitialState(): ApplicationListUiState {
        return ApplicationListUiState()
    }

    override suspend fun handleIntent(intent: ApplicationListUiIntent) {
        when (intent) {
            is ApplicationListUiIntent.LoadData -> loadApplications()
            is ApplicationListUiIntent.GoToForm -> goNavigation(
                ApplicationListNavigation.GoToForm(
                    intent.idApplication,
                    intent.nameApplication
                )
            )
        }
    }

    private fun loadApplications() {
        setUiState { copy(isLoading = true) }
        viewModelScope.launch {
            try {
                getApplicationsUseCase().collect { result ->
                    when (result) {
                        is Result.Success -> {
                            setUiState { copy(applications = result.data, isLoading = false) }
                        }

                        is Result.Error -> {
                            setUiState {
                                copy(
                                    isLoading = false,
                                    error = result.failure.toString()
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                setUiState { copy(isLoading = false, error = e.message.toString()) }
            }
        }
    }
}