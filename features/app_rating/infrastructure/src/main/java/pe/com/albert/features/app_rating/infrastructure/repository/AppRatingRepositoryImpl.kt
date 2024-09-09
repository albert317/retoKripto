package pe.com.albert.features.app_rating.infrastructure.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pe.com.albert.features.app_rating.domain.model.Application
import pe.com.albert.features.app_rating.domain.model.Property
import pe.com.albert.features.app_rating.domain.repository.AppRatingRepository
import pe.com.albert.features.app_rating.domain.util.Result
import pe.com.albert.features.app_rating.infrastructure.datasource.AppRatingDataSource
import pe.com.albert.features.app_rating.infrastructure.entity.toDomain
import pe.com.albert.features.app_rating.infrastructure.entity.toEntity
import javax.inject.Inject

class AppRatingRepositoryImpl @Inject constructor(private val appRatingDataSource: AppRatingDataSource) :
    AppRatingRepository {
    override fun getApplications(): Flow<Result<List<Application>>> {
        return appRatingDataSource.getApplications().map { result ->
            when (result) {
                is Result.Success -> Result.Success(result.data.map { it.toDomain() })
                is Result.Error -> Result.Error(result.failure)
            }
        }
    }

    override fun saveRating(propertyEntity: Property): Result<Boolean> {
        return appRatingDataSource.saveRating(propertyEntity.toEntity())
    }

    override suspend fun getAppStatistic(idApplication: String): Result<List<Property>> {
        return when (val result = appRatingDataSource.getAppStatistic(idApplication)) {
            is Result.Success -> Result.Success(result.data.map { it.toDomain() })
            is Result.Error -> Result.Error(result.failure)
        }
    }
}