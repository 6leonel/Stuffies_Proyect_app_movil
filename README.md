@ -3,131 +3,71 @@ Stuffies — Tienda de Ropa
Ropa urbana con estilo. App (y futura web) para explorar catálogo, ver promociones y contactar a la marca.

<img src="docs/screenshot_home.png" alt="Home Stuffies" width="360"/>
 Características

Home con hero (logo animado y CTA).
## Características

Promociones destacadas.
- Home con hero (logo animado y CTA).
- Promociones destacadas.
- Sección de contacto.
- UI con Jetpack Compose + Material 3.
- Soporte de imágenes/GIF/WebP animado con Coil.
- ...

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
Commit con convenciones: `feat(ui): tarjeta de promociones`  
Pull Request con descripción y capturas.

 Roadmap
## Licencia

 Catálogo con filtros y búsqueda
MIT (o la que prefieras). Ver `LICENSE`.

 Detalle de producto
## Contacto

 Carrito y checkout
Instagram: **@stuffies**  
Email: **contacto@stuffies.cl**

 Login/registro
---

 Notificaciones de ofertas
## Encargo DSY1105 — Evaluación Parcial 4

 Versión Web (Next.js/React) compartiendo diseño
1. **Nombre del proyecto**  
   Stuffies — Tienda de Ropa Urbana (App móvil Kotlin + Jetpack Compose)

 Contribución
2. **Integrantes**  
   - LEONEL ALBERTO PAVEZ PEÑA  
   - ALFONSO DAVID SUTHERLAND ABAD  
   - MIGUEL ÁNGEL TORRES URETA  

Crea una rama: feat/<tu-feature>
3. **Funcionalidades principales**  
   - Catálogo de productos con detalle (poleras, polerones, accesorios).  
   - Carrito básico en memoria (selección de productos).  
   - Blog con noticias y secciones de estilo.  
   - Mapa con ubicación de la tienda.  
   - Modo especial con preferencias guardadas en DataStore.  
   - Consumo de **API externa** (tip del día) integrado en la pantalla de Blogs.

Commit con convenciones: feat(ui): tarjeta de promociones
4. **Endpoints utilizados**

Pull Request con descripción y capturas.
   **API externa (Retrofit)**  
   - `GET https://api.adviceslip.com/advice` → devuelve un tip/frase corta que se muestra en la sección _"Tip del día"_ en Blogs.

Licencia
   **Microservicios propios (Spring Boot)**  
   > Ajusta las URLs según tu backend real.

MIT (o la que prefieras). Ver LICENSE.
   - `GET http://10.0.2.2:8080/api/productos` → lista de productos Stuffies.  
   - `GET http://10.0.2.2:8080/api/productos/{id}` → detalle de un producto.  
   - `POST http://10.0.2.2:8080/api/pedidos` → creación de pedido / carrito.  

Contacto
5. **Pasos para ejecutar la app**

Instagram: @stuffies
   1. Clonar el repositorio.  
   2. Abrir el proyecto en **Android Studio Iguana o superior**.  
   3. Sincronizar Gradle.  
   4. Ejecutar el microservicio en Spring Boot (puerto `8080`) y base de datos configurada.  
   5. Ejecutar la app en un emulador o dispositivo físico con Android 7.0+.

Email: contacto@stuffies.cl
6. **APK firmado y llave (.jks)**  
   https://imgur.com/a/kgv7AGA