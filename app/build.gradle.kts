plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "org.breezyweather.roundiconprovider"
    compileSdk = 34

    defaultConfig {
        applicationId = "org.breezyweather.roundiconprovider"
        minSdk = 11
        targetSdk = 34
        versionCode = 4
        versionName = "4.0"
    }

    buildTypes {
        named("release") {
            isShrinkResources = false
            isMinifyEnabled = false
            isDebuggable = false
            isCrunchPngs = false // No need to do that, we already optimized them
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = false
        buildConfig = false

        // Disable some unused things
        aidl = false
        renderScript = false
        shaders = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {}
