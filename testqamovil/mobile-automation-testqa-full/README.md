# mobile-automation-testqa-full

Este proyecto es un framework de automatización de pruebas móviles para una aplicación Android, utilizando Appium, Serenity BDD y Cucumber.

## ¿Qué hace este proyecto?

- Automatiza pruebas de la app móvil `saucelabs-sample.apk`.
- Ejecuta escenarios de login y flujo de compra usando pruebas escritas en Gherkin (Cucumber).
- Utiliza Appium para interactuar con un emulador o dispositivo Android.
- Genera reportes de pruebas con Serenity.

## Estructura de pruebas

- `login.feature`: Prueba el login exitoso en la app.
- `shopping.feature`: Prueba el flujo de compra agregando productos al carrito.

## Tecnologías usadas

- Java 17
- Appium (cliente Java)
- Serenity BDD
- Cucumber
- Maven

## Ejecución

1. Inicia el servidor de Appium.
2. un emulador o dispositivo Android disponible.
3. Ejecutar las pruebas con Maven:
   ```bash
   mvn clean verify
   ```
4. Los reportes se generan en `target/serenity/`.

## Configuración

- La configuración de Appium y el APK a probar se define en `serenity.conf`.

