package gnc.pages.checkout.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byAttribute;

public class DeliveryForm {
  private SelenideElement formElement;

  public DeliveryForm(SelenideElement formElement) {
    this.formElement = formElement;
  }

  public void fill(String firstName, String lastName, String postCode, String street1, String city, String phoneNumber) {
    getFirstNameField().val(firstName);
    getLastNameField().val(lastName);
    getPostcodeField().val(postCode);
    getHouseNumberField().val(street1);
    getCityField().val(city);
    getPhoneField().val(phoneNumber);
  }

  public SelenideElement getFirstNameField() {
    return formElement.find(byAttribute("name", "first-name"));
  }

  public SelenideElement getLastNameField() {
    return formElement.find(byAttribute("name", "last-name"));
  }

  public SelenideElement getPostcodeField() {
    return formElement.find("#postcode-lookup");
  }

  public SelenideElement getPhoneField() {
    return formElement.find(".formUkPhone");
  }

  public SelenideElement getCityField() {
    return formElement.find("#frm_address_town");
  }

  public SelenideElement getHouseNumberField() {
    return formElement.find("#frm_address_housenumber");
  }

  public SelenideElement shouldBe(Condition... condition) {
    return formElement.shouldBe(condition);
  }
}
