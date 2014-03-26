package gnc.spec.checkout;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import gnc.pages.MyAccountPage;
import gnc.pages.ProductDetailsPage;
import gnc.pages.checkout.BillingPageRegistered;
import gnc.spec.GNCCommonSpec;
import gnc.util.PageUtils;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static gnc.util.PageUtils.addProductToBasket;
import static org.junit.Assert.assertEquals;

public class CheckoutRegisteredSpec extends GNCCommonSpec {
  private String userFirstName = "Aliaksandr";
  private String userLastName = "Lukashenko";
  private String homePostcode = "W1T 3EF";
  private String homeAddress1 = "Home Address 1";
  private String homeCity = "Home City";
  private String homePhone = "07031897777";

  @Before
  public void registerUser() {
    PageUtils.registerUser(userFirstName, userLastName);
  }

  @Test
  public void checkBasicCheckoutFlowForRegisteredUser() throws Exception {
    BillingPageRegistered billingPage = goToCheckout();
    checkDeliveryDetailsFormErrorMessages(billingPage);

    billingPage.addDeliveryAddress("First Name", "Last Name", "W1T 3EF", "Street1", "City", "07031896976");

    createHomeAddress();
    billingPage = open("/gnc/checkout/billing.jsp", BillingPageRegistered.class);
    checkHomeAddressIsFirst(billingPage);

    billingPage.selectDeliveryAddress();
    checkHomeAddressIsSelected(billingPage);

    verifyEditAddress(billingPage);

    billingPage.selectCreditCardPaymentType();

    checkBillingDetailsFormErrorMessages(billingPage);

    billingPage.applyDeliveryAddressAsBilling();
    billingPage.payByCreditCard();
  }

  public void verifyEditAddress(BillingPageRegistered billingPage) {
    billingPage.getEditDeliveryAddressButton().click();
    billingPage.getDeliveryAddresses().get(1).find(".edit-address-link").click();
    billingPage.getDeliveryForm().fill("new first name", "new last name", "AB9 2UE" ,"new street1", "new city", "02078462856");
    billingPage.getSubmitDeliveryAddressButton().click();
    sleep(1000);
    SelenideElement updateNewAddress = billingPage.getDeliveryAddresses().get(1);
    updateNewAddress.shouldHave(text("new first name"));
    updateNewAddress.shouldHave(text("new last name"));
    updateNewAddress.shouldHave(text("AB9 2UE"));
    updateNewAddress.shouldHave(text("new street1"));
    updateNewAddress.shouldHave(text("new city"));
    updateNewAddress.shouldHave(text("02078462856"));

    billingPage.selectDeliveryAddress();
    sleep(1000);
  }

  private void checkHomeAddressIsSelected(BillingPageRegistered billingPage) {
    billingPage.getSelectedDeliveryAddress().shouldHave(text(userFirstName));
    billingPage.getSelectedDeliveryAddress().shouldHave(text(userLastName));
    billingPage.getSelectedDeliveryAddress().shouldHave(text(homePostcode));
    billingPage.getSelectedDeliveryAddress().shouldHave(text(homeCity));
    billingPage.getSelectedDeliveryAddress().shouldHave(text(homePhone));
  }

  private void createHomeAddress() {
    MyAccountPage myAccountPage = open("/account/overview", MyAccountPage.class);
    myAccountPage.updateHomeAddress(homePhone, homePostcode, homeAddress1, homeCity);
  }

  private void checkHomeAddressIsFirst(BillingPageRegistered billingPage) {
    ElementsCollection deliveryAddresses = billingPage.getDeliveryAddresses();
    assertEquals(2, deliveryAddresses.size());
    deliveryAddresses.get(0).shouldHave(text(userFirstName));
    deliveryAddresses.get(0).shouldHave(text(userLastName));
    deliveryAddresses.get(0).shouldHave(text(homeAddress1));
    deliveryAddresses.get(0).shouldHave(text(homeCity));
    deliveryAddresses.get(0).shouldHave(text(homePostcode));
    deliveryAddresses.get(0).shouldHave(text(homePhone));
  }

  private BillingPageRegistered goToCheckout() {
    ProductDetailsPage pdp = addProductToBasket("60003907");
    pdp.goToBasket().goToCheckout();

    return page(BillingPageRegistered.class);
  }

  private void checkDeliveryDetailsFormErrorMessages(BillingPageRegistered billingPage) {
    billingPage.addDeliveryAddress("", "", "1", "", "", "1");
    billingPage.deliveryFormErrors().shouldHave(text("First name is required."));
    billingPage.deliveryFormErrors().shouldHave(text("Last name is required."));
    billingPage.deliveryFormErrors().shouldHave(text("House number/name is required."));
    billingPage.deliveryFormErrors().shouldHave(text("City is required."));
    billingPage.deliveryFormErrors().shouldHave(text("Please use a valid UK postcode."));
    billingPage.deliveryFormErrors().shouldHave(text("Please use a valid UK phone number."));

    //======== bug: error label appears in incorrect place ========
//    billingPage.addDeliveryAddress(userFirstName, userLastName, "W1T 3EF", "Street1", "City", "07031896976");
//    billingPage.editAddressLink().click();
//    billingPage.submitDeliveryAddressButton().waitUntil(appears, 3000);
//    billingPage.submitDeliveryAddressButton().click();
//    billingPage.submitDeliveryAddressButton().waitUntil(disappears, 3000);
//    billingPage.editAddressLink().click();
//    billingPage.submitDeliveryAddressButton().waitUntil(appears, 3000);
//    $(".formUkPhone").val("1");
//    billingPage.submitDeliveryAddressButton().click();
//    billingPage.deliveryFormErrors().shouldHave(text("Please use a valid UK phone number."));
  }

  private void checkBillingDetailsFormErrorMessages(BillingPageRegistered billingPage) {
    billingPage.addBillingAddress("1", "", "");
    billingPage.getBillingFormErrors().shouldHave(text("Please use a valid UK postcode."));
    billingPage.getBillingFormErrors().shouldHave(text("House number/name is required."));
    billingPage.getBillingFormErrors().shouldHave(text("City is required."));
  }
}
