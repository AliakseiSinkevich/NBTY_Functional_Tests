package gnc.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;

public class ProductDetailsPage {
  public void addProductToBasket() {
    $(".purchaseButton").click();
    sleep(500);
  }

  public BasketPage goToBasket() {
    $("#ajaxedBasket").click();
    return page(BasketPage.class);
  }

  public SelenideElement getRelatedCategoriesList() {
    return $("#related-ri").find("ul");
  }
}
