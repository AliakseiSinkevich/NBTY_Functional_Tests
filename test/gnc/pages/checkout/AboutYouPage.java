package gnc.pages.checkout;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class AboutYouPage {
  public BillingPageUnregistered goToBilling(String firstName, String lastName) {
    $(byAttribute("name", "first-name")).val(firstName);
    $(byAttribute("name", "last-name")).val(lastName);

    $(byValue("Continue")).click();
    return page(BillingPageUnregistered.class);
  }

  public void fillPasswordFieldsToCreateAccount(String password) {
    $("#password").val(password);
    $("#confirm-password").val(password);
    if (getDontCreateAccountCheckbox().isSelected()) {
      getDontCreateAccountCheckbox().click();
    }
  }

  public SelenideElement getDontCreateAccountCheckbox() {
    return $("#checkout_form_no_account");
  }

  public SelenideElement getCreateAccountForm() {
    return $(".toggleContent");
  }

  public SelenideElement getReceiveDiscountsCheckbox() {
    return $(byAttribute("name", "contact-email"));
  }

  public SelenideElement getErrorMessagesBox() {
    return $(".errorBox");
  }
}
