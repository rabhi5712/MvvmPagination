plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.mvvmpagination"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mvvmpagination"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val activityKtxVersion = "1.9.1"
    val lifecycleVersion = "2.2.0"
    val viewModelVersion = "2.8.5"
    val retrofitVersion = "2.9.0"
    val interceptorVersion = "4.10.0"
    val scalarVersion = "2.7.2"
    val coroutinesCoreVersion = "1.8.0"
    val coroutineAndroidVersion = "1.8.0"
    val hiltVersion = "2.48"

    //Activity KTX for viewModels
    implementation("androidx.activity:activity-ktx:$activityKtxVersion")

    //ViewModels
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$viewModelVersion")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$interceptorVersion")
    implementation("com.squareup.retrofit2:converter-scalars:$scalarVersion")

//Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesCoreVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineAndroidVersion")

    //dagger-hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt(libs.hilt.android.compiler)

    //Secure
    implementation(libs.androidx.security.crypto.ktx)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
}