Stuffies — Tienda de Ropa

Ropa urbana con estilo. App (y futura web) para explorar catálogo, ver promociones y contactar a la marca.

<img src="docs/screenshot_home.png" alt="Home Stuffies" width="360"/>

## Características

- Home con hero (logo animado y CTA).
- Promociones destacadas.
- Sección de contacto.
- UI con Jetpack Compose + Material 3.
- Soporte de imágenes/GIF/WebP animado con Coil.
- ...

Commit con convenciones: `feat(ui): tarjeta de promociones`  
Pull Request con descripción y capturas.

## Licencia

MIT (o la que prefieras). Ver `LICENSE`.

## Contacto

Instagram: **@stuffies**  
Email: **contacto@stuffies.cl**

---

## Encargo DSY1105 — Evaluación Parcial 4

1. **Nombre del proyecto**  
   Stuffies — Tienda de Ropa Urbana (App móvil Kotlin + Jetpack Compose)

2. **Integrantes**  
   - LEONEL ALBERTO PAVEZ PEÑA  
   - ALFONSO DAVID SUTHERLAND ABAD  
   - MIGUEL ÁNGEL TORRES URETA  

3. **Funcionalidades principales**  
   - Catálogo de productos con detalle (poleras, polerones, accesorios).  
   - Carrito básico en memoria (selección de productos).  
   - Blog con noticias y secciones de estilo.  
   - Mapa con ubicación de la tienda.  
   - Modo especial con preferencias guardadas en DataStore.  
   - Consumo de **API externa** (tip del día) integrado en la pantalla de Blogs.

4. **Endpoints utilizados**

   **API externa (Retrofit)**  
   - `GET https://api.adviceslip.com/advice` → devuelve un tip/frase corta que se muestra en la sección _"Tip del día"_ en Blogs.

   **Microservicios propios (Spring Boot)**  
   > Ajusta las URLs según tu backend real.

   - `GET http://10.0.2.2:8080/api/productos` → lista de productos Stuffies.  
   - `GET http://10.0.2.2:8080/api/productos/{id}` → detalle de un producto.  
   - `POST http://10.0.2.2:8080/api/pedidos` → creación de pedido / carrito.  

5. **Pasos para ejecutar la app**

   1. Clonar el repositorio.  
   2. Abrir el proyecto en **Android Studio Iguana o superior**.  
   3. Sincronizar Gradle.  
   4. Ejecutar el microservicio en Spring Boot (puerto `8080`) y base de datos configurada.  
   5. Ejecutar la app en un emulador o dispositivo físico con Android 7.0+.

6. **APK firmado y llave (.jks)**  
   - Ubicación sugerida del APK firmado: `./apk/Stuffies-release.apk`  
   - Ubicación sugerida de la llave `.jks`: `./keystore/stuffies-release.jks`  
   - Agrega en esta carpeta las capturas de pantalla del proceso de firma para la defensa.

> **Nota:** Ajusta las URLs/endpoints y cualquier detalle técnico según tu implementación real antes de entregar el encargo.
