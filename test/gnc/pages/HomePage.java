package gnc.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class HomePage {
  public SelenideElement getMyGNC() {
    return $("#mygncbar");
  }

  public SignInPage openSignInPage() {
    $(".signin").find("a").click();
    return page(SignInPage.class);
  }

  public SelenideElement getRecentlyViewedSection() {
    return $("#bar-recentlyviewed");
  }
}
