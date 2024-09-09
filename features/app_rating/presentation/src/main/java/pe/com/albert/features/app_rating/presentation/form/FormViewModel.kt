package pe.com.albert.features.app_rating.presentation.form

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pe.com.albert.features.app_rating.domain.model.Property
import pe.com.albert.features.app_rating.domain.usecase.SaveRatingUseCase
import pe.com.albert.features.app_rating.presentation.util.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FormViewMode @Inject constructor(
    private val saveRatingUseCase:SaveRatingUseCase
):BaseViewModel<FormUiState, FormEvent, FormUiIntent, FormNavigation>(){
    override fun createInitialState(): FormUiState {
       return FormUiState()
    }

    override suspend fun handleIntent(intent: FormUiIntent) {
        when(intent){
            is FormUiIntent.SaveProperty -> saveRating(intent.property)
        }
    }

    private fun saveRating(property: Property){
        setUiState { copy(isLoading = true) }
        viewModelScope.launch {
            try {
                saveRatingUseCase(property)
                setUiState { copy(isLoading = false) }
                goNavigation(FormNavigation.GoToApplicationList)
            } catch (e: Exception) {
                setUiState { copy(isLoading = false, error = e.message.toString()) }
            }
        }
    }
}