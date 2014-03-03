package gnc.pages.checkout;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import gnc.pages.checkout.elements.DeliveryForm;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BillingPageRegistered extends BillingPage {
  public void addDeliveryAddress(String firstName, String lastName, String postCode, String street1, String city, String phoneNumber) {
    if (getEditDeliveryAddressButton().exists()) {
      getEditDeliveryAddressButton().click();
    }

    if (getAddDeliveryAddressButton().isDisplayed()) {
      getAddDeliveryAddressButton().click();
    }
    getSubmitDeliveryAddressButton().waitUntil(appears, 3000);

    getDeliveryForm().fill(firstName, lastName, postCode, street1, city, phoneNumber);

    getSubmitDeliveryAddressButton().click();
  }

  public SelenideElement getSubmitDeliveryAddressButton() {
    return $("#newAddressSubmit");
  }

  public SelenideElement getEditDeliveryAddressButton() {
    return $("#editShippingAddress");
  }

  public void selectDeliveryAddress() {
    $("#save-shipping-address").click();
    $("#cardPaymentMethod").waitUntil(appears, 3000);
  }

  public void selectCreditCardPaymentType() {
    $("#cardPaymentMethod").click();
//    $("#submitBillingAddress").waitUntil(appears, 3000);
  }

  public void addBillingAddress(String postCode, String street1, String city) {
    getBillingForm().fill(postCode, street1, city);

    $("#submitBillingAddress").click();
  }

  public void applyDeliveryAddressAsBilling() {
    $("#checkout_form_use_delivery").waitUntil(appears, 3000);
    $("#checkout_form_use_delivery").click();
    $("#submitBillingAddress").click();
    $("#editBillingAddress").waitUntil(appears, 3000);
    $("#cardType").waitUntil(appears, 3000);
  }

  public void payByCreditCard() {
    $("#checkout_card_type").selectOptionByValue("Visa");
    $("iframe").waitUntil(appears, 20000);
  }

  public SelenideElement getAddDeliveryAddressButton() {
    return $(".adddelivery");
  }

  public SelenideElement getBillingFormErrors() {
    return $("#billingAddressErrors");
  }

  public SelenideElement getNewAddressForm() {
    return $("#newAddressForm");
  }

  public ElementsCollection getDeliveryAddresses() {
    $(".address-list").waitUntil(appears, 3000);
    return $$(".address-block");
  }

  @Override
   public DeliveryForm getDeliveryForm() {
    getSubmitDeliveryAddressButton().waitUntil(appears, 3000);
    return new DeliveryForm($("#newAddressForm"));
  }
}
