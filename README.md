# 🌟 SimuWear - Tu Compañero de Bienestar

Una aplicación integral para Wear OS que combina entretenimiento, salud, funciones sociales y herramientas de productividad, diseñada específicamente para dispositivos wearables.

## 👥 Integrantes del Equipo

- González Llamosas Noe Ramsés
- Hernández Hernández Roberto Isaac.

---

## 📱 Descripción

SimuWear es una aplicación completa para Wear OS que ofrece cuatro módulos principales diseñados para mejorar tu experiencia diaria con el smartwatch:

- **🎮 Entretenimiento**: Juegos interactivos y citas inspiradoras
- **💚 Salud & Bienestar**: Seguimiento de hidratación, ejercicio y recordatorios de salud
- **👥 Social**: Notificaciones sociales y respuestas rápidas
- **⚡ Utilidades**: Gestión de tareas, timer Pomodoro y alertas contextuales

## ✨ Características Principales

### 🎮 Módulo de Entretenimiento
- **💭 Citas Inspiradoras**: Frases motivacionales rotativas para inspirarte durante el día
- **🧠 Juego de Memoria**: Ejercita tu mente con un juego de emparejar emojis
- **🧩 Puzzle Numérico**: Resuelve puzzles deslizantes para mantener tu mente activa

### 💚 Módulo de Salud
- **💧 Contador de Hidratación**: Seguimiento de vasos de agua (0-8 vasos diarios)
- **🏃 Tracker de Ejercicio**: Registro de minutos de actividad física
- **⏰ Recordatorios Inteligentes**: Alertas personalizables para agua, ejercicio y descanso
- **📊 Progreso Visual**: Indicadores claros de tus metas de salud

### 👥 Módulo Social
- **📱 Notificaciones Rápidas**: Vista de notificaciones sociales recientes
- **⚡ Respuestas Rápidas**: Botones de respuesta instantánea (👍 OK / ❌ NO)
- **📍 Compartir Ubicación**: Funcionalidad de ubicación integrada

### ⚡ Módulo de Utilidades
- **📋 Gestor de Tareas**: Lista de tareas con check/uncheck interactivo
- **⏱️ Timer Pomodoro**: 
  - Sesiones de 25 minutos de trabajo
  - Descansos de 5 minutos
  - Descanso largo de 15 minutos cada 4 pomodoros
  - Contador de pomodoros completados
- **🌅 Alertas Contextuales**: Mensajes automáticos basados en la hora del día
- **📍 Alertas de Ubicación**: Recordatorios basados en localización

## 🛠️ Tecnologías Utilizadas

### Frontend
- **Kotlin** - Lenguaje principal de desarrollo
- **Jetpack Compose for Wear OS** - UI moderna y declarativa
- **Wear Compose Material** - Componentes específicos para wearables
- **Navigation Compose** - Navegación entre pantallas

### Arquitectura
- **MVVM Pattern** - Separación clara de responsabilidades
- **State Management** - Manejo de estado con Compose State
- **Coroutines** - Programación asíncrona
- **Lifecycle Components** - Gestión del ciclo de vida

### Desarrollo
- **Android Studio** - IDE de desarrollo
- **Gradle KTS** - Build system con Kotlin DSL
- **Android Gradle Plugin 8.6.1** - Herramientas de build actualizadas

## 📋 Requisitos del Sistema

### Dispositivo
- **Wear OS 3.0+** (API level 30+)
- **Pantalla redonda o cuadrada** compatible
- **RAM**: Mínimo 1GB recomendado
- **Almacenamiento**: 50MB de espacio libre

### Desarrollo
- **Android Studio** Flamingo (2022.2.1) o superior
- **JDK 21** o superior
- **Android SDK** level 35
- **Wear OS Emulator** para pruebas

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio
```bash
git clone https://github.com/tu-usuario/SimuWear.git
cd SimuWear
```

### 2. Configurar el Entorno
```bash
# Verificar versión de Java
java -version

# Abrir en Android Studio
# File > Open > Seleccionar carpeta SimuWear
```

### 3. Configurar Emulador
1. Abrir **AVD Manager** en Android Studio
2. Crear un **Wear OS Device**:
   - Template: **Wear OS Large Round** o **Wear OS Square**
   - API Level: **30** (Android 11) o superior
   - RAM: **1GB** mínimo

### 4. Build y Ejecución
```bash
# Limpiar proyecto
./gradlew clean

# Compilar
./gradlew build

# Instalar en emulador/dispositivo
./gradlew installDebug
```

## 📁 Estructura del Proyecto

```
SimuWear/
├── app/
│   ├── src/main/java/com/simu_wear/app/
│   │   ├── MainActivity.kt                 # Actividad principal
│   │   ├── ui/theme/
│   │   │   └── Theme.kt                   # Tema de la aplicación
│   │   └── screens/                       # Pantallas de la app
│   │       ├── HomeScreen.kt              # Pantalla principal
│   │       ├── EntertainmentScreen.kt     # Módulo entretenimiento
│   │       ├── HealthScreen.kt            # Módulo salud
│   │       ├── SocialScreen.kt            # Módulo social
│   │       ├── UtilitiesScreen.kt         # Módulo utilidades
│   │       ├── QuoteGameScreen.kt         # Juego de citas
│   │       ├── MemoryGameScreen.kt        # Juego de memoria
│   │       ├── PuzzleGameScreen.kt        # Puzzle numérico
│   │       ├── PomodoroTimerScreen.kt     # Timer Pomodoro
│   │       ├── TaskManagerScreen.kt       # Gestor de tareas
│   │       └── WaterReminderScreen.kt     # Recordatorios
│   ├── src/main/res/
│   │   ├── drawable/                      # Iconos y recursos gráficos
│   │   ├── mipmap/                        # Iconos de launcher
│   │   ├── values/                        # Colores, strings, estilos
│   │   └── xml/                           # Configuraciones XML
│   └── build.gradle.kts                   # Configuración de build
├── gradle/
│   └── libs.versions.toml                 # Versiones de dependencias
├── build.gradle.kts                       # Build script raíz
└── settings.gradle.kts                    # Configuración del proyecto
```

## 🎨 Diseño y UX

### Principios de Diseño
- **Optimización para Wearables**: Interfaces adaptadas a pantallas pequeñas
- **Navegación Intuitiva**: Gestos de swipe y botones grandes
- **Accesibilidad**: Colores contrastantes y texto legible
- **Consistencia Visual**: Paleta de colores unificada

### Paleta de Colores
```kotlin
primary_blue     = #2196F3    // Azul principal
primary_green    = #4CAF50    // Verde salud/éxito
primary_purple   = #9C27B0    // Morado entretenimiento
primary_orange   = #FF9800    // Naranja alertas/utilidades
background_dark  = #000000    // Fondo oscuro
surface_dark     = #1E1E1E    // Superficies
```

### Casos de Prueba Principales
- ✅ Navegación entre módulos
- ✅ Funcionalidad de contadores (agua, ejercicio)
- ✅ Lógica de juegos (memoria, puzzle)
- ✅ Timer Pomodoro y gestión de estado
- ✅ Persistencia de datos de tareas

## 🚀 Deployment

### Configuración de Firma
1. Crear keystore para firma de aplicación
2. Configurar `signing` en `build.gradle.kts`
3. Añadir variables de entorno para credenciales

## 🔄 Próximas Funcionalidades

- [ ] **Sincronización con la nube** para backup de datos
- [ ] **Integración con Google Fit** para métricas avanzadas de salud
- [ ] **Notificaciones push** reales con Firebase
- [ ] **Personalización de temas** (modo claro/oscuro)
- [ ] **Widgets de pantalla principal** para acceso rápido
- [ ] **Reconocimiento de actividad** automático
- [ ] **Integración con calendario** para recordatorios
- [ ] **Modo offline** completo para todas las funciones

---
<div align="center">

**SimuWear** - Desarrollado con ❤️ para la comunidad Wear OS

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-purple.svg)](https://kotlinlang.org/)
[![Wear OS](https://img.shields.io/badge/Wear%20OS-3.0+-green.svg)](https://wearos.google.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Build](https://img.shields.io/badge/Build-Passing-brightgreen.svg)]()

</div>
