@hotel
Feature: Reservación de hotel

  Scenario: Reservar el hotel más económico con filtros específicos
    Given el usuario navega a la página de hoteles
    When aplica los filtros de búsqueda
    And selecciona el hotel más económico
    Then valida el mensaje de reservación exitosa