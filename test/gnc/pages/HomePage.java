package gnc.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomePage {
  public SelenideElement getMyGNC() {
    return $("#mygncbar");
  }

  public SignInPage openSignInPage() {
    return open($(".signin").find("a").getAttribute("href"), SignInPage.class);
  }

  public SelenideElement getRecentlyViewedSection() {
    return $("#bar-recentlyviewed");
  }
}
