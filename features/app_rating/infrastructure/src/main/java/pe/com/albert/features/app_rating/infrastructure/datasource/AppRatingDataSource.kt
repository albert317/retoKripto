package pe.com.albert.features.app_rating.infrastructure.datasource

import kotlinx.coroutines.flow.Flow
import pe.com.albert.features.app_rating.domain.util.Result
import pe.com.albert.features.app_rating.infrastructure.entity.ApplicationEntity
import pe.com.albert.features.app_rating.infrastructure.entity.PropertyEntity

interface AppRatingDataSource {
    fun getApplications(): Flow<Result<List<ApplicationEntity>>>
    fun saveRating(propertyEntity: PropertyEntity): Result<Boolean>
    suspend fun getAppStatistic(idApplication: String): Result<ApplicationEntity>
}