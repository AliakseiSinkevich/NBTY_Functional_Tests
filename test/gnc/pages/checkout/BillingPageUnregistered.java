package gnc.pages.checkout;

import gnc.pages.checkout.elements.DeliveryForm;

import static com.codeborne.selenide.Selenide.$;

public class BillingPageUnregistered extends BillingPage {
  public void addDeliveryAddress(String firstName, String lastName, String postCode, String street1, String city, String phoneNumber) {
    getDeliveryForm().fill(firstName, lastName, postCode, street1, city, phoneNumber);

    $("#deliverButtonId").click();
  }

  @Override
  public DeliveryForm getDeliveryForm() {
    return new DeliveryForm($("#shippingAddressForm"));
  }
}
