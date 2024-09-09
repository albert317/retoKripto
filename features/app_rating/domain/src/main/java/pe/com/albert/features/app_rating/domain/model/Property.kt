package pe.com.albert.features.app_rating.domain.model

data class Property(
    val id: String,
    val idApplication: Int,
    val cpuResources: Float,
    val memoryResources: Float,
    val frequencyUse: Int,
    val isRedundant: Boolean,
    val isObsolete: Boolean,
    val lastUpdate: String
)