package pe.com.albert.features.app_rating.domain.model

data class Property(
    val id: String? = null,
    val idApplication: String? = null,
    val cpuResources: String? = null,
    val memoryResources: String? = null,
    val frequencyUse: String? = null,
    val isRedundant: Boolean = false,
    val isObsolete: Boolean = false,
    val lastUpdate: String? = null,
    val cantidadEncuentados: Int? = 0
)