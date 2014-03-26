package gnc.util;

import com.codeborne.selenide.SelenideElement;
import gnc.pages.HomePage;
import gnc.pages.ProductDetailsPage;
import gnc.pages.RegistrationPage;
import org.openqa.selenium.By;
import util.PropertiesManager;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PageUtils {
  private PageUtils() {}

  public static void signInAsDefaultUser() {
    HomePage homePage = open("/", HomePage.class);
    signOut();
    homePage.openSignInPage().signIn(PropertiesManager.getTestLogin(), PropertiesManager.getDefaultPassword());
  }

  public static String registerUser(String firstName, String lastName) {
    return registerUser(generateRandomEmail(), firstName, lastName);
  }

  public static String registerUser(String email, String firstName, String lastName) {
    signOut();
    RegistrationPage registrationPage = open("/account/registration", RegistrationPage.class);
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