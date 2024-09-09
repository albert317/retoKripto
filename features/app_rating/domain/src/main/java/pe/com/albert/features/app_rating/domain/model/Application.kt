package pe.com.albert.features.app_rating.domain.model

data class Application(
    val id: String,
    val name: String,
    val description: String,
    val version: String,
    val dateCreation: String,
)

val exampleData = Application(
    id = "1",
    name = "App 1",
    description = "Description 1",
    version = "1.0",
    dateCreation = "2021-10-10"
)