plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.simu_wear.app"
    compileSdk = 35 // ACTUALIZADO de 34 a 35

    defaultConfig {
        applicationId = "com.simu_wear.app"
        minSdk = 30
        targetSdk = 34 // Mantener en 34 por compatibilidad
        versionCode = 1
        versionName = "1.0"
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
        kotlinCompilerExtensionVersion = rootProject.extra["composeVersion"] as String
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val composeVersion = rootProject.extra["composeVersion"] as String
    val wearComposeVersion = rootProject.extra["wearComposeVersion"] as String

    // Wear OS Core
    implementation("androidx.wear:wear:1.3.0")
    implementation("androidx.wear.compose:compose-material:$wearComposeVersion")
    implementation("androidx.wear.compose:compose-foundation:$wearComposeVersion")
    implementation("androidx.wear.compose:compose-navigation:$wearComposeVersion")
    implementation("androidx.wear.compose:compose-ui-tooling:$wearComposeVersion")

    // Compose BOM - VERSION ACTUALIZADA
    implementation(platform("androidx.compose:compose-bom:2024.09.03"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui-tooling")

    // Activity & Lifecycle - VERSIONES COMPATIBLES
    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6") // DOWNGRADE para compatibilidad
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6") // DOWNGRADE para compatibilidad

    // Navigation - VERSION COMPATIBLE
    implementation("androidx.navigation:navigation-compose:2.8.2") // DOWNGRADE para compatibilidad

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")

    // Data Storage
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // WorkManager para notificaciones
    implementation("androidx.work:work-runtime-ktx:2.9.1")

    // Permisos
    implementation("com.google.accompanist:accompanist-permissions:0.32.0")

    // Health & Sensors
    implementation("androidx.health:health-services-client:1.0.0-beta03")

    // SplashScreen API
    implementation("androidx.core:core-splashscreen:1.0.1")
}