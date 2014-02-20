package gnc.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class ProductDetailsPage {
  public void addProductToBasket() {
    $(".purchaseButton").click();
    sleep(1000);
  }

  public BasketPage goToBasket() {
    $("#ajaxedBasket").click();
    return page(BasketPage.class);
  }
}
