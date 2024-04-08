plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt") //kapt
    id("com.google.devtools.ksp").version("1.6.10-1.0.4") //ksp
}

android {
    namespace = "com.example.quotesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.quotesapp"
        minSdk = 24
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    //Moshi
    implementation("com.squareup.retrofit2:converter-moshi:2.10.0")

    //moshi
    implementation("com.squareup.moshi:moshi:1.15.1")

    //ksp
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")


    //kapt
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")

    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //glide
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")




    //Room Database
    fun room_version() = "2.6.1"

    implementation("androidx.room:room-runtime:${room_version()}")
    annotationProcessor("androidx.room:room-compiler:${room_version()}")


    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:${room_version()}")


    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:${room_version()}")

    //Navigation
    fun nav_version() = "2.7.7"

    implementation("androidx.navigation:navigation-compose:${nav_version()}")
}