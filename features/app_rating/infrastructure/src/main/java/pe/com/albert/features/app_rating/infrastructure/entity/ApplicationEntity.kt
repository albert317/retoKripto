package pe.com.albert.features.app_rating.infrastructure.entity

import pe.com.albert.features.app_rating.domain.model.Application

data class ApplicationEntity(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val version: String = "",
    val dateCreation: String = "",
)

fun ApplicationEntity.toDomain() = Application(
    id = id,
    name = name,
    description = description,
    version = version,
    dateCreation = dateCreation
)

fun Application.toEntity() = ApplicationEntity(
    id = id,
    name = name,
    description = description,
    version = version,
    dateCreation = dateCreation
)