# testapi

Este proyecto es una API de pruebas automatizadas desarrollada en Java, utilizando herramientas como Maven, Serenity BDD, Cucumber y JUnit. Su objetivo principal es facilitar la validación y verificación de funcionalidades de una API a través de pruebas automatizadas.

## Estructura del proyecto
- **src/test/java/**: Contiene el código fuente de las pruebas automatizadas, organizadas en paquetes.
- **src/test/resources/features/**: Incluye los archivos feature de Cucumber, donde se describen los escenarios de prueba en lenguaje Gherkin.
- **pom.xml**: Archivo de configuración de Maven, donde se gestionan las dependencias y plugins necesarios para la ejecución de las pruebas.
- **target/**: Carpeta generada automáticamente por Maven que contiene los resultados de la compilación, los reportes de pruebas y otros archivos temporales.

## Ejecución de pruebas
Para ejecutar las pruebas automatizadas, utiliza el siguiente comando en la raíz del proyecto:

```bash
mvn clean verify
```

Esto generará los reportes de Serenity en `target/site/serenity/`.

## Reportes
Los resultados de las pruebas se pueden visualizar en formato HTML accediendo a los archivos dentro de `target/site/serenity/`.

## Tecnologías utilizadas
- Java
- Maven
- Serenity BDD
- Cucumber
- JUnit

## Objetivo
El objetivo de este proyecto es proporcionar una base sólida para la automatización de pruebas de APIs, permitiendo validar endpoints.
