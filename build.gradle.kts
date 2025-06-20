buildscript {
    val composeVersion by extra("1.5.8")
    val wearComposeVersion by extra("1.2.1")

    dependencies {
        classpath("com.android.tools.build:gradle:8.6.1") // ACTUALIZADO de 8.2.0 a 8.6.1
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
    }
}

plugins {
    id("com.android.application") version "8.6.1" apply false // ACTUALIZADO
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}