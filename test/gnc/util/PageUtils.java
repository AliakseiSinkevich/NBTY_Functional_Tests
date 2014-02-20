package gnc.util;

import com.codeborne.selenide.SelenideElement;
import gnc.pages.ProductDetailsPage;
import gnc.pages.RegistrationPage;
import org.openqa.selenium.By;
import util.PropertiesManager;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PageUtils {
  public static String registerUser(String firstName, String lastName) {
    signOut();
    RegistrationPage registrationPage = open("/account/registration", RegistrationPage.class);
    String email = generateRandomEmail();
    registrationPage.register(firstName, lastName, email, PropertiesManager.getDefaultPassword());
    return email;
  }

  public static String generateRandomEmail() {
    return "selenide-test@" + System.currentTimeMillis() + "gmail.com";
  }

  public static void signOut() {
    open("/");
    SelenideElement signOutLink = $(By.linkText("Sign out"));
    if (signOutLink.exists()) {
      signOutLink.click();
    }
  }

  public static ProductDetailsPage addProductToBasket(String productId) {
    ProductDetailsPage pdp = open(getProductUrl(productId), ProductDetailsPage.class);
    pdp.addProductToBasket();
    return pdp;
  }

  public static String getProductUrl(String productId) {
    return "/shop/product/-" + productId;
  }
}