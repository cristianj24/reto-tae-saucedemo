package pe.interbank.saucedemo.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import pe.interbank.saucedemo.ui.ProductsPage;

public class IsProductsPageVisible {
    public static Question<Boolean> titleProductsPage(){
        return actor -> Visibility.of(ProductsPage.TITLE).answeredBy(actor);
    }
}
