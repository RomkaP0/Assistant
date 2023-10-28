plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.huawei.agconnect")
}


android {
    namespace = "com.romka_po.assistent"
    compileSdk = 34

    flavorDimensions+= "services"
    productFlavors {
        create("hms") {
            dimension  = "services"
        }
        create("gms") {
            dimension = "services"
        }
    }

    sourceSets {
        getByName("hms") {
            java {
                srcDirs("src\\hms\\java", "src\\hms\\java")
            }
            res {
                srcDirs("src\\hms\\res", "src\\hms\\res")
            }
        }
        getByName("gms") {
            java {
                srcDirs("src\\gms\\java", "src\\gms\\java")
            }
            res {
                srcDirs("src\\gms\\res", "src\\gms\\res")
            }
        }
    }

    defaultConfig {
        applicationId = "com.romka_po.assistent"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        manifestPlaceholders["YANDEX_CLIENT_ID"] = "b23df7b682dc438d8d5ca5dd9bd2ec96"

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
        viewBinding = true
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
    implementation("androidx.work:work-runtime-ktx:2.9.0-rc01")
    val compose = "1.6.0-alpha08"
    val composeBom = "2023.10.01"

    val material3 = "1.2.0-alpha10"

    val navigation = "2.7.4"

    val hilt = "2.48.1"

    val retrofit = "2.9.0"

    val room = "2.6.0"

    val hiltAndroid = "1.1.0-rc01"


    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")


    /*Compose*/
    implementation(platform("androidx.compose:compose-bom:${composeBom}"))
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.compose.material3:material3:$material3")
    implementation("androidx.compose.foundation:foundation:$compose")
    implementation("androidx.compose.ui:ui-viewbinding:$compose")
    androidTestImplementation(platform("androidx.compose:compose-bom:${composeBom}"))

    /*Compose UI*/
    implementation("androidx.compose.ui:ui:${compose}")
    implementation("androidx.compose.ui:ui-graphics:${compose}")
    implementation("androidx.compose.ui:ui-tooling-preview:${compose}")
    implementation("androidx.compose.material:material-icons-extended:${compose}")

    /*Coil*/
    implementation("io.coil-kt:coil-compose:2.4.0")


    /*Compose Test*/
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${compose}")
    debugImplementation("androidx.compose.ui:ui-tooling:${compose}")
    debugImplementation("androidx.compose.ui:ui-test-manifest:${compose}")


    /*Hilt*/
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-rc01")

    implementation ("com.google.dagger:hilt-android:2.48.1")

    implementation("androidx.work:work-runtime-ktx:2.9.0-rc01")
    implementation("androidx.work:work-runtime:2.9.0-rc01")
    implementation("androidx.hilt:hilt-work:1.1.0-rc01")

    ksp ("com.google.dagger:hilt-android-compiler:2.48.1")
    ksp("androidx.hilt:hilt-compiler:1.1.0-rc01")

    /*Datastore*/
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    /*Navigation*/
    implementation("androidx.navigation:navigation-compose:$navigation")

    /*Map*/
    implementation("ru.mail.maps:mapkit:1.0.308")


    /*Http-request*/
    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")

    /*Database*/
    implementation("androidx.room:room-ktx:$room")
    implementation("androidx.room:room-runtime:$room")
    ksp("androidx.room:room-compiler:$room")

    /*GMS*/
    "gmsImplementation" ("com.google.android.gms:play-services-location:21.0.1")
    "gmsImplementation" ("com.google.android.gms:play-services-auth:20.7.0")

    /*HMS*/
    "hmsImplementation" ("com.huawei.agconnect:agconnect-core:1.9.1.301")
    "hmsImplementation" ("com.huawei.hms:location:6.12.0.300")

    /*VK*/
    implementation ("com.vk:android-sdk-core:4.1.0")
    implementation ("com.vk:android-sdk-api:4.1.0")

    /*Yandex*/
    implementation ("com.yandex.android:authsdk:3.0.0")

    /*chart*/
    implementation("com.carlosgub.kotlinm.charts:charts:1.0.4")

    /*Test*/
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

}