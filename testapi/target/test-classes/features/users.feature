Feature: API User Management

  Scenario: Obtener datos del usuario con id 10
    Given el usuario consulta la lista de usuarios
    When obtiene el usuario con id 10
    Then visualiza el phone, email y company del usuario
