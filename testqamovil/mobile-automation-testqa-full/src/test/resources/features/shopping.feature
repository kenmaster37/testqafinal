Feature: Flujo de compra en la app

  Scenario: Comprar 3 productos aleatorios
  Given el usuario abre la app
    When ingresa usuario  "standard_user" y contraseña "secret_sauce"
    And presiona el botón de login
    And agrega un producto al carrito
    Then el producto debería aparecer en el carrito
