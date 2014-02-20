package gnc.pages.checkout;

import com.codeborne.selenide.SelenideElement;
import gnc.pages.checkout.elements.BillingForm;
import gnc.pages.checkout.elements.DeliveryForm;

import static com.codeborne.selenide.Condition.appears;
import static com.codeborne.selenide.Selenide.$;

public abstract class BillingPage {
  public SelenideElement deliveryFormErrors() {
    return $("#deliveryFormErrors");
  }

  public abstract DeliveryForm getDeliveryForm();

  public BillingForm getBillingForm() {
    $("#submitBillingAddress").waitUntil(appears, 7000);
    return new BillingForm($("#billingAddressForm"));
  }

  public SelenideElement getSelectedDeliveryAddress() {
    return $("#deliveryAddress");
  }
}
