package pe.interbank.saucedemo.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ProductsPage {
    public static final Target TITLE = Target.the("products title").located(By.className("title"));
}
