package pe.interbank.saucedemo.stepsDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import pe.interbank.saucedemo.questions.IsErrorMessageVisible;
import pe.interbank.saucedemo.questions.IsProductsPageVisible;
import pe.interbank.saucedemo.tasks.Login;
import pe.interbank.saucedemo.tasks.NavigateTo;
import pe.interbank.saucedemo.tdm.UsuarioTdm;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginStepsDefs {
    @Given("que el {actor} abre la página de SauceDemo")
    public void queElUsuarioAbreLaPáginaDeSauceDemo(Actor actor) {
        actor.attemptsTo(
                NavigateTo.sauceDemoPage()
        );
    }
    @When("inicia sesion con las credenciales {string}, {string}")
    public void iniciaSesionConLasCredenciales(String usuario, String contrasena) {
        if ("tdm".equals(contrasena)) {
            UsuarioTdm.Usuario userData = UsuarioTdm.obtenerUsuarioPorUsername(usuario);
            if (userData != null) {
                theActorInTheSpotlight().attemptsTo(Login.withCredentials(userData.username, userData.password));
            } else {
                throw new RuntimeException("Usuario no encontrado en TDM: " + usuario);
            }
        } else {
            theActorInTheSpotlight().attemptsTo(Login.withCredentials(usuario, contrasena));
        }

    }
    @Then("el login es satisfactorio")
    public void elLoginEsSatisfactorio() {
        theActorInTheSpotlight().should(
                seeThat("El titulo de la pagina productos es visible", IsProductsPageVisible.titleProductsPage(),equalTo(true))
        );
    }
    @Then("debería ver un mensaje de error")
    public void deberíaVerUnMensajeDeError() {
        theActorInTheSpotlight().should(
                seeThat("El mensaje de error es visible", IsErrorMessageVisible.IsMessageVisible(),equalTo(true))
        );
    }
}
