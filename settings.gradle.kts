pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
//        maven(url = uri("https://maven.pkg.github.com/geors/maps-sdk-android")) {
//            credentials {
//                username = "RomkaP0"
//                password = "RS62725a01b2f3438f845134ee0d554736edda1e4234c62208271e1a11814d53"
//            }
//        }
    }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://artifactory.2gis.dev/sdk-maven-release"))
        maven(url = uri("https://artifactory-external.vkpartner.ru/artifactory/maps-sdk-android/"))
    }
}

rootProject.name = "Assistent"
include(":app")
 