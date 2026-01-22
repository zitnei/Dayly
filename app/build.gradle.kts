    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.ksp)
        alias(libs.plugins.kotlin.compose)
    }

    android {
        namespace = "com.rhythmlife.app"
        compileSdk = 35

        defaultConfig {
            applicationId = "com.rhythmlife.app"
            minSdk = 26
            targetSdk = 35
            versionCode = 1
            versionName = "1.0"
        }

        buildFeatures {
            compose = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }
    }
    dependencies {

        implementation(platform("androidx.compose:compose-bom:2024.09.00"))
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.ui:ui-tooling-preview")
        debugImplementation("androidx.compose.ui:ui-tooling")
        implementation("androidx.compose.material3:material3")

        implementation("androidx.activity:activity-compose:1.8.0")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
        implementation("androidx.navigation:navigation-compose:2.8.5")

        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")


    }
