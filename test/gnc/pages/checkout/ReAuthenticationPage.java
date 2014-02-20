package gnc.pages.checkout;

import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ReAuthenticationPage {
  public AboutYouPage goToAboutYou(String email) {
    if ($("#wrongEmailLink").isDisplayed()) {
      $("#wrongEmailLink").click();
    }

    $("#checkout_form_email").val(email);
    $(byValue("Continue")).click();
    return page(AboutYouPage.class);
  }
}
