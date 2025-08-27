Feature: API Restful Booker Management

  Scenario: Consultar la lista de reservas
    Given el usuario consulta la lista de reservas

  Scenario: Crear, actualizar y eliminar una reserva
    When crea una nueva reserva
    And actualiza la reserva
    And elimina la reserva
    Then la operación de reserva es exitosa
