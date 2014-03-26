package gnc.spec;

import com.codeborne.selenide.ElementsCollection;
import gnc.pages.HomePage;
import gnc.util.PageUtils;
import org.junit.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static gnc.util.PageUtils.getProductUrl;
import static gnc.util.PageUtils.signOut;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static gnc.util.PageUtils.clearCookie;
import static gnc.util.PageUtils.getCookieByName;
import static util.PropertiesManager.getRecentlyViewedCookieName;

public class RecentlyViewedProductsSpec extends GNCCommonSpec {
  private String[] productIds = {"60083511", "60092886", "60007573"};

  @Test
  public void recentlyViewedProductsShouldBeSavedInCookiesForTransientProfile() throws Exception {
    signOut();
    clearCookie(getRecentlyViewedCookieName());

    visitProducts();

    Cookie recentlyViewedCookie = getCookieByName(getRecentlyViewedCookieName());
    assertEquals("," + productIds[0] + "," + productIds[1] + "," + productIds[2] + ",", recentlyViewedCookie.getValue());

    checkRecentlyViewedInMyGNC(productIds[2], productIds[1]);
  }

  @Test
  public void dontDisplayRecentlyViewedSectionIfItIsEmpty() throws Exception {
    signOut();
    clearCookie(getRecentlyViewedCookieName());

    HomePage homePage = open("/", HomePage.class);

    homePage.getRecentlyViewedSection().shouldNotBe(visible);
  }

  @Test
  public void checkRecentlyViewedForLoggedInUser() throws Exception {
    PageUtils.registerUser("Vasya", "Pupkin");
    visitProducts();
    checkRecentlyViewedInMyGNC(productIds[2], productIds[1]);
  }

  private void visitProducts() {
    open(getProductUrl(productIds[0]));
    open(getProductUrl(productIds[0]));
    open(getProductUrl(productIds[1]));
    open(getProductUrl(productIds[2]));
  }

  private void checkRecentlyViewedInMyGNC(String firstProductId, String secondProductId) {
    HomePage homePage = open("/", HomePage.class);
    ElementsCollection links = homePage.getRecentlyViewedSection().find(".reveal").findAll("a");
    assertEquals(2, links.size());
    assertTrue(links.get(0).getAttribute("href").endsWith("-" + firstProductId));
    assertTrue(links.get(1).getAttribute("href").endsWith("-" + secondProductId));
  }
}
