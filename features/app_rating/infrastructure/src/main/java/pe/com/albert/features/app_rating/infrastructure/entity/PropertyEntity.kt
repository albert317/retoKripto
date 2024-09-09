package pe.com.albert.features.app_rating.infrastructure.entity

import pe.com.albert.features.app_rating.domain.model.Property

data class PropertyEntity(
    val id: String? = null,
    val idApplication: String? = null,
    val cpuResources: String? = null,
    val memoryResources: String? = null,
    val frequencyUse: String? = null,
    val isRedundant: Boolean = false,
    val isObsolete: Boolean = false,
    val lastUpdate: String? = null
)

fun PropertyEntity.toDomain() = Property(
    id = id,
    idApplication = idApplication,
    cpuResources = cpuResources,
    memoryResources = memoryResources,
    frequencyUse = frequencyUse,
    isRedundant = isRedundant,
    isObsolete = isObsolete,
    lastUpdate = lastUpdate
)

fun Property.toEntity() = PropertyEntity(
    id = id,
    idApplication = idApplication,
    cpuResources = cpuResources,
    memoryResources = memoryResources,
    frequencyUse = frequencyUse,
    isRedundant = isRedundant,
    isObsolete = isObsolete,
    lastUpdate = lastUpdate
)