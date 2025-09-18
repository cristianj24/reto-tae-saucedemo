@Login
Feature: Login en SauceDemo

  Como usuario de SauceDemo
  Quiero poder iniciar sesión
  Para acceder a la aplicación

  @LoginSuccessful
  Scenario: Login exitoso con credenciales válidas
    Given que el usuario abre la página de SauceDemo
    When inicia sesion con las credenciales "standard_user", "tdm"
    Then el login es satisfactorio

  @LoginFailed
  Scenario Outline: Login exitoso con credenciales incorrectas
    Given que el usuario abre la página de SauceDemo
    When inicia sesion con las credenciales "<usuario>", "<contrasenia>"
    Then debería ver un mensaje de error
    Examples:
      | usuario         | contrasenia |
      | locked_out_user | tdm         |
      | wrong_user      | tdm         |