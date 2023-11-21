pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://developer.huawei.com/repo/") }

    }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev"))

        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/carlosgub/kotlinm-charts")

            credentials {
                username = "RomkaP0"
                password = "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"
            }
        }
        maven(url = uri("https://artifactory-external.vkpartner.ru/artifactory/maps-sdk-android/"))
        maven {
            url = uri("https://artifactory-external.vkpartner.ru/artifactory/superappkit-maven-public/")
        }
        maven { url = uri("https://developer.huawei.com/repo/") }
    }

}

rootProject.name = "Assistent"
include(":app")
 