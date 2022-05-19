plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("kotlin-android-extensions")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id ("androidx.navigation.safeargs")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintlayout)
    implementation(Google.material)

    testImplementation (Testing.junit)
    androidTestImplementation (Testing.junitExt)
    androidTestImplementation (Testing.espresso)

    // Dagger Hilt
    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    // Room
    implementation (Room.roomRuntime)
    kapt (Room.roomCompiler)

    // Kotlin Extensions and Coroutines support for Room
    implementation (Room.roomKtx)

    // Coroutines
    implementation (AndroidX.coroutinesCore)
    implementation (AndroidX.coroutinesAndroid)

    // Coroutine Lifecycle Scopes
    implementation (AndroidX.lifecycleViewmodel)
    implementation (AndroidX.lifecycleRuntime)

    // Retrofit
    implementation (Retrofit.retrofit)
    implementation (Retrofit.gsonConverter)
    implementation (Retrofit.okHttpLoggingInterceptor)

    // Navigation Components
    implementation (AndroidX.navigationFragmentKtx)
    implementation (AndroidX.navigationUiKtx)

    // Glide
    implementation (Glide.gilde)
    kapt (Glide.gildeCompiler)

    implementation(project(Modules.core))
    implementation(project(Modules.breakingNews))
    implementation(project(Modules.savedNews))
    implementation(project(Modules.newsDetails))
}