buildscript {
    val agp_version by extra("8.2.0-rc03")
    dependencies {
        // Add the Android Gradle plugin configuration. You need to replace {version} with the actual Gradle plugin version, for example, 7.1.1.
        classpath("com.android.tools.build:gradle:$agp_version")
        // Add the AppGallery Connect plugin configuration. Please refer to AppGallery Connect Plugin Dependency to select a proper plugin version.
        classpath("com.huawei.agconnect:agcp:1.9.1.301")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0-rc03" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.dagger.hilt.android") version "2.48.1" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
}