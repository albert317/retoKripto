package pe.com.albert.features.app_rating.infrastructure.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import pe.com.albert.features.app_rating.domain.util.Failure
import pe.com.albert.features.app_rating.domain.util.Result
import pe.com.albert.features.app_rating.infrastructure.datasource.AppRatingDataSource
import pe.com.albert.features.app_rating.infrastructure.entity.ApplicationEntity
import pe.com.albert.features.app_rating.infrastructure.entity.PropertyEntity
import javax.inject.Inject

class AppRatingDataSourceImpl @Inject constructor(private val fireStore: FirebaseFirestore) :
    AppRatingDataSource {
    override fun getApplications(): Flow<Result<List<ApplicationEntity>>> {
        return callbackFlow {
            val document =
                fireStore.collection("Application").addSnapshotListener { value, error ->
                    if (error != null) {
                        trySend(Result.Error(Failure.CustomError(error.message ?: "Unknown Error")))
                    } else {
                        val applications = value?.documents?.mapNotNull { document ->
                            document.toObject(ApplicationEntity::class.java)?.copy(id = document.id)
                        }
                        if (applications != null) {
                            trySend(Result.Success(applications.sortedBy { it.dateCreation }))
                        } else {
                            trySend(Result.Error(Failure.CustomError("not found")))
                        }
                    }
                }
            awaitClose {
                document.remove()
            }
        }
    }

    override fun saveRating(propertyEntity: PropertyEntity): Result<Boolean> {
        return try {
            fireStore.collection("Property").add(propertyEntity)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(Failure.CustomError(e.message ?: "Unknown Error"))
        }
    }

    override suspend fun getAppStatistic(idApplication: String): Result<List<PropertyEntity>> {
        return try {
            val document = fireStore.collection("Property")
                .whereEqualTo("idApplication", idApplication)
                .get()
                .await()

            val properties = document.documents.mapNotNull {
                val objetc = it.toObject(PropertyEntity::class.java)?.copy(id = it.id)
                objetc
            }
            Result.Success(properties)
        } catch (e: Exception) {
            Result.Error(Failure.CustomError(e.message ?: "Unknown Error"))
        }
    }
}