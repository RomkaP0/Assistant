plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.romka_po.assistent"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.romka_po.assistent"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val compose = "1.6.0-alpha06"
    val composeBom = "2023.09.00"

    val material3 = "1.2.0-alpha08"

    val navigation = "2.7.2"

    val hilt = "2.47"

    val retrofit = "2.9.0"

    val room = "2.5.2"


    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")


    /*Compose*/
    implementation(platform("androidx.compose:compose-bom:${composeBom}"))
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.material3:material3:$material3")
    implementation("androidx.compose.foundation:foundation:$compose")
    androidTestImplementation(platform("androidx.compose:compose-bom:${composeBom}"))

    /*Compose UI*/
    implementation("androidx.compose.ui:ui:${compose}")
    implementation("androidx.compose.ui:ui-graphics:${compose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${compose}")
    implementation("androidx.compose.material:material-icons-extended:${compose}")

    /*Compose Test*/
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${compose}")
    debugImplementation("androidx.compose.ui:ui-tooling:${compose}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${compose}")


    /*Hilt*/
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation ("com.google.dagger:hilt-android:$hilt")
    ksp ("com.google.dagger:hilt-android-compiler:$hilt")

    /*Datastore*/
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    /*Navigation*/
    implementation("androidx.navigation:navigation-compose:$navigation")

    /*Map*/
    implementation ("com.yandex.android:maps.mobile:4.3.2-full")
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation ("ru.dgis.sdk:sdk-full:7.3.1")
    implementation("ru.mail.maps:mapkit:1.0.308")


    /*Http-request*/
    implementation("com.squareup.retrofit2:retrofit:$retrofit")

    /*Database*/
    implementation("androidx.room:room-runtime:$room")
    ksp("androidx.room:room-compiler:$room")

    /*Test*/
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}