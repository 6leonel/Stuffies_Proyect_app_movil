Stuffies — Tienda de Ropa

Ropa urbana con estilo. App (y futura web) para explorar catálogo, ver promociones y contactar a la marca.

<img src="docs/screenshot_home.png" alt="Home Stuffies" width="360"/>
 Características

Home con hero (logo animado y CTA).

Promociones destacadas.

Sección de contacto.

UI con Jetpack Compose + Material 3.

Soporte de imágenes/GIF/WebP animado con Coil.

Roadmap corto: carrito, detalle de producto, favoritos, auth, checkout, integración de pasarela (Mercado Pago/Stripe), panel admin.

 Tech Stack

Kotlin, Jetpack Compose, Material 3

Coil (coil-compose, coil-gif) para imágenes y animaciones

Gradle Kotlin DSL

Min SDK/Target SDK según build.gradle.kts

 Requisitos

Android Studio Iguana o superior

JDK 17 (viene con AS)

Emulador o dispositivo Android


Cómo ejecutar

Clona el repo:

git clone https://github.com/<tu-usuario>/<tu-repo>.git
cd <tu-repo>


Abre en Android Studio.

Sincroniza Gradle (Sync Now).

Ejecuta en un emulador (API 28+) o dispositivo.

 Build/Tests (opcional)
./gradlew assembleDebug
./gradlew test

 Estructura (resumen)
app/
 └─ src/main/
    ├─ java/com/example/stuffies_proyect_grupo_6/
    │   ├─ MainActivity.kt
    │   └─ ui/theme/...
    ├─ res/
    │   ├─ drawable/           # imágenes (incluye stuffies_anim.webp)
    │   ├─ values/             # colors.xml, strings.xml, themes.xml
    │   └─ xml/                # backup_rules, data_extraction_rules
    └─ AndroidManifest.xml

 UI/Assets

Logo animado: res/drawable/stuffies_anim.webp

Para mostrarlo en Compose:

import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale

AsyncImage(
    model = R.drawable.stuffies_anim,
    contentDescription = "Logo animado",
    contentScale = ContentScale.Fit
)

⚙️ Dependencias clave
// build.gradle.kts (Module: app)
dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.09.03"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("io.coil-kt:coil-compose:2.7.0")
    implementation("io.coil-kt:coil-gif:2.7.0")
    // ...
}

 Permisos

Si cargas imágenes desde Internet:

<!-- AndroidManifest.xml (fuera de <application>) -->
<uses-permission android:name="android.permission.INTERNET" />

 Roadmap

 Catálogo con filtros y búsqueda

 Detalle de producto

 Carrito y checkout

 Login/registro

 Notificaciones de ofertas

 Versión Web (Next.js/React) compartiendo diseño

 Contribución

Crea una rama: feat/<tu-feature>

Commit con convenciones: feat(ui): tarjeta de promociones

Pull Request con descripción y capturas.

Licencia

MIT (o la que prefieras). Ver LICENSE.

Contacto

Instagram: @stuffies

Email: contacto@stuffies.cl
