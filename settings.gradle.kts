pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://artifactory-external.vkpartner.ru/artifactory/maps-sdk-android/"))
    }
}

rootProject.name = "Assistent"
include(":app")
 