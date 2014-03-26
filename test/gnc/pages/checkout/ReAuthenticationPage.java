package gnc.pages.checkout;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ReAuthenticationPage {
  public AboutYouPage goToAboutYou(String email) {
    openEmailEnteringFormIfRequired();
    getEmailInput().val(email);
    getSubmitEmailButton().click();
    return page(AboutYouPage.class);
  }

  public SelenideElement getEmailInput() {
    return $("#checkout_form_email");
  }

  public void openEmailEnteringFormIfRequired() {
    if ($("#wrongEmailLink").isDisplayed()) {
      $("#wrongEmailLink").click();
    }
  }

  public SelenideElement getErrorsBox() {
    return $("#checkoutForm");
  }

  public SelenideElement getSubmitEmailButton() {
    return $(byValue("Continue"));
  }
}
