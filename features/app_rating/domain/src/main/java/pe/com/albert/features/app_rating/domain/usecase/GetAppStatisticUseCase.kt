package pe.com.albert.features.app_rating.domain.usecase

import pe.com.albert.features.app_rating.domain.repository.AppRatingRepository
import javax.inject.Inject

class GetAppStatisticUseCase @Inject constructor(
    private val appRatingRepository: AppRatingRepository
) {
    suspend operator fun invoke(idApplication:String) = appRatingRepository.getAppStatistic(idApplication)
}