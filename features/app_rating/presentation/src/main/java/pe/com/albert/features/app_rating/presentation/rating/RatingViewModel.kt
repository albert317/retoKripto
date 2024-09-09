package pe.com.albert.features.app_rating.presentation.rating

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.com.albert.features.app_rating.domain.usecase.GetAppStatisticUseCase
import pe.com.albert.features.app_rating.presentation.util.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val getAppStatisticUseCase: GetAppStatisticUseCase
) : BaseViewModel<RatingUiState, RatingEvent, RatingUiIntent, RatingNavigation>() {
    override fun createInitialState(): RatingUiState {
        return RatingUiState()
    }

    override suspend fun handleIntent(intent: RatingUiIntent) {
        when (intent) {
            is RatingUiIntent.LoadData -> loadAppStatistic(intent.isApplication)

        }
    }

    private fun loadAppStatistic(idApplication: String) {
        setUiState { copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val result = getAppStatisticUseCase(idApplication)
                setUiState { copy(property = result, isLoading = false) }
            } catch (e: Exception) {
                setUiState { copy(isLoading = false, error = e.message.toString()) }
            }
        }
    }
}
