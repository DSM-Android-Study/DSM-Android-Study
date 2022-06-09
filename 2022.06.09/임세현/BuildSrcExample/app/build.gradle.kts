plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = Versions.COMPILE_SDK
    buildToolsVersion = Versions.BUILD_TOOLS_VERSION

    defaultConfig {
        applicationId = Versions.APPLICATION_ID
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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
    // AndroidX
    implementation(Dependency.AndroidX.APP_COMPAT)
    implementation(Dependency.AndroidX.MATERIAL)
    implementation(Dependency.AndroidX.CONSTRAINT_LAYOUT)

    // KTX
    implementation(Dependency.KTX.CORE)

    // TEST
    testImplementation(Dependency.Test.JUNIT)

    // AndroidTest
    androidTestImplementation(Dependency.AndroidTest.ESPRESSO_CORE)
}