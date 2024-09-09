package pe.com.albert.features.app_rating.infrastructure.entity

import pe.com.albert.features.app_rating.domain.model.Property

data class PropertyEntity(
    val id: String,
    val idApplication: Int,
    val cpuResources: Float,
    val memoryResources: Float,
    val frequencyUse: Int,
    val isRedundant: Boolean,
    val isObsolete: Boolean,
    val lastUpdate: String
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