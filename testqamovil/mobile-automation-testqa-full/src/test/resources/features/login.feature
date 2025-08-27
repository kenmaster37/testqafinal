@login
Feature: Login en la app

  Scenario: Login exitoso
    Given el usuario abre la app
    When ingresa usuario  "standard_user" y contraseña "secret_sauce"
    And presiona el botón de login
    Then debería ingresar exitosamente


