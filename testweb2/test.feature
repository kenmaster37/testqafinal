# language: es

Característica: Pruebas de la aplicación DXHotels

  Escenario: Login exitoso
    Dado que el usuario está en la página de login
    Cuando ingresa credenciales válidas
    Entonces accede correctamente al sistema

  Escenario: Login fallido
    Dado que el usuario está en la página de login
    Cuando ingresa credenciales inválidas
    Entonces se muestra un mensaje de error

  Escenario: Reservación de hotel
    Dado que el usuario está autenticado y en la página de búsqueda de hoteles
    Cuando realiza una búsqueda con los siguientes filtros:
      | checkin         | checkout        | habitaciones | adultos | niños | precio_min | estrellas_min |
      | +2 días actual | +7 días actual | 2            | 3       | 2     | 200        | 3            |
    Y aplica los filtros seleccionados
    Y selecciona el hotel más económico que cumpla con los filtros
    Y realiza la reservación
    Entonces se valida el mensaje de reservación exitosa
