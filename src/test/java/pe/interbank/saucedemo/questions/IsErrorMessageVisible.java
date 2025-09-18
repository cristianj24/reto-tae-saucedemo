package pe.interbank.saucedemo.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import pe.interbank.saucedemo.ui.LoginPage;
import pe.interbank.saucedemo.ui.ProductsPage;

public class IsErrorMessageVisible {
    public static Question<Boolean> IsMessageVisible(){
        return actor -> Visibility.of(LoginPage.ERROR_MESSAGE).answeredBy(actor);
    }
}
