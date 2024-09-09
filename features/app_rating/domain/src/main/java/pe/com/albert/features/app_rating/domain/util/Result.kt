package pe.com.albert.features.app_rating.domain.util

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val failure: Failure) : Result<Nothing>()
}