package gnc.spec;

import org.junit.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Page404Spec extends GNCCommonSpec {
  @Test
  public void verify404PageIsDisplayedForInvalidUrls() throws Exception {
    open("/some_invalid_url");
    verify404PageIsDisplayed();

    open("/shop/invalid_url");
    verify404PageIsDisplayed();
  }

  private void verify404PageIsDisplayed() {
    $(byText("404 PAGE NOT FOUND")).shouldBe(visible);
  }
}
