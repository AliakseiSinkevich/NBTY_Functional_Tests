package gnc.pages.checkout.elements;

import com.codeborne.selenide.SelenideElement;

public class BillingForm {
  private SelenideElement formElement;

  public BillingForm(SelenideElement formElement) {
    this.formElement = formElement;
  }

  public void fill(String postCode, String street1, String city) {
    getPostcodeField().val(postCode);
    getHouseNumberField().val(street1);
    getCityField().val(city);
  }

  public SelenideElement getPostcodeField() {
    return formElement.find("#postcode-lookup");
  }

  public SelenideElement getCityField() {
    return formElement.find("#frm_address_town");
  }

  public SelenideElement getHouseNumberField() {
    return formElement.find("#frm_address_housenumber");
  }
}
