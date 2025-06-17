# Add project specific ProGuard rules here.
-keep class androidx.wear.** { *; }
-keep class androidx.compose.** { *; }
-dontwarn androidx.wear.**

# Mantener clases de navegación
-keep class androidx.navigation.** { *; }
-keep class androidx.lifecycle.** { *; }

# Mantener clases de datos personalizadas
-keep class com.simu_wear.app.screens.** { *; }

# Reglas para Kotlin Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Reglas para evitar warnings
-dontwarn kotlin.Unit
-dontwarn kotlin.coroutines.jvm.internal.DebugMetadataKt
