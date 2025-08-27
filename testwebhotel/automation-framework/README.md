# Automation Framework - Serenity BDD + JUnit + Cucumber

Este proyecto es un framework de automatización de pruebas web usando Java, Maven, Selenium WebDriver, Cucumber, JUnit y Serenity BDD para reportes avanzados, no se tomo en cuenta el login debido a que no responde a claves comunes y no se a podido validar el uso del capcha.

## Estructura principal
- `src/main/java/driver/DriverFactory.java`: Manejo centralizado de WebDriver (ThreadLocal).
- `src/main/java/pageObjects/Base_PO.java`: Page Object base para utilidades comunes.
- `src/test/java/stepDefinitions/`: Definiciones de pasos Cucumber.
- `src/test/java/runners/SerenityCucumberIT.java`: Runner principal con integración Serenity.
- `src/test/resources/features/`: Archivos feature de Cucumber.
- `serenity.properties`: Configuración de Serenity.
- `pom.xml`: Dependencias y plugins Maven.



## Ejecución de pruebas
1. **Compilar y ejecutar pruebas:**
   ```bash
   mvn clean verify && mvn serenity:aggregate
   ```
2. **Ver reporte Serenity:**
   archivo generado en:
   ```
   target/serenity-reports/index.html
   ```

## Resultados esperados
- Se ejecutan los escenarios definidos en los features.
- El reporte HTML de Serenity muestra el detalle de cada ejecución.


