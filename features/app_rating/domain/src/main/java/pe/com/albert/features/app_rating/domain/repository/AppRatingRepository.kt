package pe.com.albert.features.app_rating.domain.repository

import kotlinx.coroutines.flow.Flow
import pe.com.albert.features.app_rating.domain.model.Application
import pe.com.albert.features.app_rating.domain.model.Property
import pe.com.albert.features.app_rating.domain.util.Result

interface AppRatingRepository {
    fun getApplications(): Flow<Result<List<Application>>>
    fun saveRating(propertyEntity: Property): Result<Boolean>
    suspend fun getAppStatistic(idApplication: String): Result<List<Property>>
}