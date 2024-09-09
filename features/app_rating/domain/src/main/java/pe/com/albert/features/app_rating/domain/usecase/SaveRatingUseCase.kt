package pe.com.albert.features.app_rating.domain.usecase

import pe.com.albert.features.app_rating.domain.model.Property
import pe.com.albert.features.app_rating.domain.repository.AppRatingRepository
import javax.inject.Inject

class SaveRatingUseCase @Inject constructor(
    private val appRatingRepository: AppRatingRepository
) {
    operator fun invoke(property: Property) = appRatingRepository.saveRating(property)
}