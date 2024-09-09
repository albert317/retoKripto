package pe.com.albert.features.app_rating.domain.usecase

import pe.com.albert.features.app_rating.domain.repository.AppRatingRepository
import javax.inject.Inject

class GetApplicationsUseCase @Inject constructor(
    private val appRatingRepository: AppRatingRepository
) {
    operator fun invoke() = appRatingRepository.getApplications()
}