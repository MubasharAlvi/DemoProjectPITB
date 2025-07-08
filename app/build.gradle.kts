import com.android.build.api.dsl.ProductFlavor

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "2.2.0"
//    id("com.google.devtools.ksp") version "1.9.22-1.0.20"
    id("com.google.devtools.ksp") version "1.9.22-1.0.18" apply false
    alias(libs.plugins.kotlin.compose)

}


android {
    namespace = "com.example.newprojectforhamza"
    compileSdk = 36

    buildFeatures {
        compose =true
        buildConfig = true
        viewBinding =true
        dataBinding =true
    }

    defaultConfig {
        applicationId = "com.example.newprojectforhamza"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }
        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a")
        }
    }
    composeOptions { kotlinCompilerExtensionVersion = "2.0.0" }

    productFlavors{

   }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
        }
    }

/**
    productFlavors {
        production {
            resValue "string", "app_name", "Jet"
            versionCode 1
            versionName '1.0.0'
            multiDexEnabled true
            signingConfig signingConfigs.config
                    externalNativeBuild {
                        cmake {
                            cppFlags ""
                            arguments "-DANDROID_STL=c++_shared"
                        }
                    }
        }



        staging {
            resValue "string", "app_name", "Stagging Jet"
            applicationIdSuffix ".staging"
            versionCode 1
            versionName '1.0.0'
            multiDexEnabled true
            signingConfig signingConfigs.config
        }

        android.applicationVariants.all { variant ->

            variant.outputs.all {output ->
                def appName = "JET"
                def variantName = variant.name.capitalize()
                def versionName = variant.versionName
                        def versionCode = variant.versionCode
                        def date = new Date()
                def formattedDate = date.format('dd-M-YY')
                def newName = "${appName}-${variantName}-v${versionName}(${versionCode})-(${formattedDate}).apk"
                output.outputFileName = newName

            }
        }
    }
*/

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    /** ---------- Unit testing ---------- */
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    /** ---------- AndroidX Room testing ---------- */
    testImplementation(libs.androidx.room.testing)
    /** ---------- Flow / Turbine ---------- */
    testImplementation(libs.turbine)

    /** Security */
    implementation (libs.androidx.security.crypto)

    /** Sqlcipher  */
    implementation (libs.android.database.sqlcipher)
    implementation(libs.androidx.sqlite)

    /**Dagger Hilt*/
    implementation (libs.hilt.android)
    kapt (libs.hilt.android.compiler)
    /** Hilt For instrumentation tests*/
    androidTestImplementation (libs.hilt.android.testing)
    kaptAndroidTest (libs.hilt.android.compiler)
    /** Hilt For local unit tests*/
    testImplementation (libs.hilt.android.testing)
    kaptTest (libs.hilt.compiler)

    /**Retrofit*/
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.okhttp)
    implementation (libs.logging.interceptor)

    /**Coroutines*/
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    /**View Model*/
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)

    /** Navigation  */
    // Views/Fragments integration
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
  /**  // Feature module support for Fragments
   // implementation("androidx.navigation:navigation-dynamic-features-fragment:2.9.0")*/
    androidTestImplementation(libs.androidx.navigation.testing)
    implementation(libs.kotlinx.serialization.json)
   /***/
    implementation(libs.glide)

    /** Splash Screen */
    implementation(libs.androidx.core.splashscreen)

    /**Serialization*/
    implementation(libs.kotlinx.serialization.core)
    implementation(libs.kotlinx.serialization.json)

    /** Room Database */
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.rxjava2)
    implementation(libs.androidx.room.rxjava3)
    implementation(libs.androidx.room.guava)
    testImplementation(libs.androidx.room.testing)
    implementation(libs.androidx.room.paging)

    /** RecycleView */
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.recyclerview.selection)
    testImplementation(kotlin("test"))

    /** ----   Compose Dependency ------*/
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.coil3.coil.compose)
    implementation(libs.coil.network.okhttp)
}

kapt {
    correctErrorTypes= true
}