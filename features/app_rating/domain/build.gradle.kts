plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {
    implementation(libs.javaxInject)
    implementation(libs.coroutines.android)
}