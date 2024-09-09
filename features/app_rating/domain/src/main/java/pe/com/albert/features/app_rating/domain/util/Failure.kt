package pe.com.albert.features.app_rating.domain.util

sealed class Failure {
    data object NetworkConnection : Failure()
    data object ServerError : Failure()
    data class CustomError(val errorMessage: String) : Failure()
}