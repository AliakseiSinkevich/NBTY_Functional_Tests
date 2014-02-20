package gnc.pages;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class BasketPage {
  public void goToCheckout() {
    $(byText("Checkout now")).click();
  }
}
