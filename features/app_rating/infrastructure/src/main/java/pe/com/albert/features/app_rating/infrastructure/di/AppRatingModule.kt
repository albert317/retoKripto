package pe.com.albert.features.app_rating.infrastructure.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.com.albert.features.app_rating.domain.repository.AppRatingRepository
import pe.com.albert.features.app_rating.infrastructure.datasource.AppRatingDataSource
import pe.com.albert.features.app_rating.infrastructure.datasource.impl.AppRatingDataSourceImpl
import pe.com.albert.features.app_rating.infrastructure.repository.AppRatingRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
class AppRatingModule {

}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppRatingBinds {
    @Binds
    abstract fun bindAppRatingDataSource(appRatingDataSourceImpl: AppRatingDataSourceImpl): AppRatingDataSource

    @Binds
    abstract fun bindAppRatingRepository(appRatingRepositoryImpl: AppRatingRepositoryImpl): AppRatingRepository
}