package util;

import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static util.PropertiesManager.getRecentlyViewedCookieName;

public class CustomFunctions {
  public static Cookie getCookieByName(String name) {
    return getWebDriver().manage().getCookieNamed(name);
  }

  public static void clearCookie(String cookieName) {
    setCookie(new Cookie(getRecentlyViewedCookieName(), ""));
  }

  public static void setCookie(Cookie cookie) {
    getWebDriver().manage().addCookie(cookie);
  }
}
