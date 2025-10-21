// settings.gradle.kts
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    // Lee versiones desde gradle.properties o -P... (con defaults seguros)
    val agp = providers.gradleProperty("agpVersion").orElse("8.12.1").get()
    val kotlinVer = providers.gradleProperty("kotlinVersion").orElse("2.0.20").get()

    resolutionStrategy {
        eachPlugin {
            when {
                // Fuerza AGP seleccionado
                requested.id.id.startsWith("com.android.") ->
                    useModule("com.android.tools.build:gradle:$agp")

                // (Opcional) fuerza plugins Kotlin si quieres unificar versión
                requested.id.id.startsWith("org.jetbrains.kotlin.") ->
                    useVersion(kotlinVer)
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Stuffies_Proyect_app_movil"
include(":app")
