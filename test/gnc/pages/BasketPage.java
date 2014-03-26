package gnc.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class BasketPage {
  public void goToCheckout() {
    $(".checkout-btn").click();
    sleep(500);
  }
}
