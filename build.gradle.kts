buildscript {
    val agp_version by extra("8.2.0-beta04")
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0-beta04" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"


}

configurations.all{
    resolutionStrategy{
        force("com.google.android.gms:play-services-location:21.0.1")
    }
}