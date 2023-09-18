// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.3.0-alpha03" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false

}

configurations.all{
    resolutionStrategy{
        force("com.google.android.gms:play-services-location:21.0.1")
    }
}