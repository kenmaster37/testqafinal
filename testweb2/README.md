# DXHotels Automation

Este proyecto automatiza pruebas de la aplicación web https://demos.devexpress.com/rwa/dxhotels/ usando Serenity BDD, Cucumber, Gradle y el patrón POM.

## Estructura del proyecto
- `test.feature`: Escenarios Gherkin para login y reservación.
- `src/test/java/com/dxhotels/pages`: Clases de páginas (POM).
- `src/test/java/com/dxhotels/stepdefinitions`: Definiciones de pasos Cucumber.
- `src/test/java/com/dxhotels/runners`: Runner de pruebas.
- `build.gradle`: Configuración de dependencias y plugins.

## Ejecución de pruebas

1. Instala Java 11+ y Gradle.
2. Ejecuta:
   ```bash
   ./gradlew clean test
   ```
3. Los reportes se generan en `build/reports/serenity`.

## Escenarios implementados
- Login exitoso: usuario Sam / sam.
- Login fallido: usuario/clave inválidos.
- Reservación de hotel: filtros y validación de mensaje exitoso.

### Defectos encontrados
- El mensaje de error de login no es suficientemente descriptivo para usuarios finales.

## Variables y buenas prácticas
- Variables y selectores están centralizados en las clases de página.
- Se usa el patrón POM para mantenibilidad.
- Los features están escritos en español.

## Subir a GitHub
1. Inicializa el repo:
   ```bash
   git init
   git add .
   git commit -m "Proyecto de automatización DXHotels"
   git branch -M main
   git remote add origin <URL-de-tu-repo>
   git push -u origin main
   ```

## Contacto
Para dudas o mejoras, abre un issue en el repositorio.
