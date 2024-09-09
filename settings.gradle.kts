pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
rootProject.name = "Kripto"
include(":app")
include(":core")
include(":core:database")
include(":features")

include(":features:app_rating")
include(":features:app_statistics")
include(":features:app_rating:presentation")
include(":features:app_rating:domain")
include(":features:app_rating:infrastructure")
include(":core:firebase")
