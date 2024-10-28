plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.myapp009adatastore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapp009adatastore"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Opravené závislosti pro DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0") // DataStore Preferences
    implementation("androidx.datastore:datastore-core:1.0.0") // DataStore Core

    // Přidání ViewModel a Coroutines
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1") // ViewModel KTX
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1") // Lifecycle KTX
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0") // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0") // Kotlin Coroutines for Android

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
