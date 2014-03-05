package gnc.spec.checkout;

import com.codeborne.selenide.Configuration;
import gnc.pages.ProductDetailsPage;
import gnc.pages.checkout.AboutYouPage;
import gnc.pages.checkout.BillingPageRegistered;
import gnc.pages.checkout.BillingPageUnregistered;
import gnc.pages.checkout.ReAuthenticationPage;
import gnc.spec.GNCCommonSpec;
import gnc.util.PageUtils;
import org.junit.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.sleep;
import static gnc.util.PageUtils.generateRandomEmail;
import static gnc.util.PageUtils.signInAsDefaultUser;
import static gnc.util.PageUtils.signOut;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static util.CustomFunctions.clearCookie;
import static util.PropertiesManager.getDefaultPassword;

public class CheckoutUnregisteredSpec extends GNCCommonSpec {
  String userFirstName = "Aliaksandr";
  String userLastName = "Lukashenko";

  @Test
  public void checkBasicUnregisteredCheckout() throws Exception {
    Configuration.holdBrowserOpen = true;

    AboutYouPage aboutYouPage = goToCheckoutUnregistered();
    checkMessagesOnAboutYouPage(aboutYouPage);

    BillingPageUnregistered billingPage = aboutYouPage.goToBilling(userFirstName, userLastName);
    billingPage.getDeliveryForm().shouldBe(visible);
    billingPage.getDeliveryForm().getFirstNameField().shouldHave(value(userFirstName));
    billingPage.getDeliveryForm().getLastNameField().shouldHave(value(userLastName));

    checkDeliveryFormErrorMessages(billingPage);

    billingPage.addDeliveryAddress(userFirstName, userLastName, "W1T 3EF", "Street1", "City", "07031896976");
//    todo
  }

  /**
   * <h3>Scenario</h3>
   * Unregistered user created account on "About you" page.
   * <h3>Expected behavior (NBTYCOMM-1347)</h3>
   * On billing page the same delivery form as for transient user should be shown.
   * The same scenario for the registered user who doesn't have any delivery address
   */
  @Test
  public void checkDeliveryFormForRegisteredOnCheckoutUser() throws Exception {
    Configuration.holdBrowserOpen = true;
    AboutYouPage aboutYouPage = goToCheckoutUnregistered();
    aboutYouPage.fillPasswordFieldsToCreateAccount(getDefaultPassword());
    aboutYouPage.goToBilling("Vasya", "Pupkin");
    BillingPageRegistered billingPage = page(BillingPageRegistered.class);
    billingPage.getAddDeliveryAddressButton().shouldNotBe(visible);
    billingPage.getNewAddressForm().shouldBe(visible);

    billingPage.getDeliveryForm().getFirstNameField().shouldHave(value("Vasya"));
    billingPage.getDeliveryForm().getLastNameField().shouldHave(value("Pupkin"));

    billingPage.addDeliveryAddress("A", "B", "W1T 3EF", "Street1", "City", "07031896976");
    billingPage.getAddDeliveryAddressButton().shouldBe(visible);
  }


  protected AboutYouPage goToCheckoutUnregistered() {
    signOut();
    signInAsDefaultUser();
    signOut();
    clearCookie("luc");
    ProductDetailsPage pdp = PageUtils.addProductToBasket("60003907");
    pdp.goToBasket().goToCheckout();
    ReAuthenticationPage reAuthenticationPage = page(ReAuthenticationPage.class);

    checkAuthenticationPageErrorMessages(reAuthenticationPage);

    return reAuthenticationPage.goToAboutYou(generateRandomEmail());
  }

  private void checkAuthenticationPageErrorMessages(ReAuthenticationPage reAuthenticationPage) {
    reAuthenticationPage.openEmailEnteringForm();
    sleep(500);
    reAuthenticationPage.getSubmitEmailButton().click();
    reAuthenticationPage.getErrorsBox().shouldHave(text("Email is required."));
    reAuthenticationPage.getEmailInput().val("invalid_email");
    reAuthenticationPage.getSubmitEmailButton().click();
    reAuthenticationPage.getErrorsBox().shouldHave(text("Email is not valid."));
  }

  protected void checkDeliveryFormErrorMessages(BillingPageUnregistered billingPage) {
    billingPage.addDeliveryAddress("", "", "1", "", "", "1");
    billingPage.deliveryFormErrors().shouldHave(text("First name is required."));
    billingPage.deliveryFormErrors().shouldHave(text("Last name is required."));
    billingPage.deliveryFormErrors().shouldHave(text("Please use a valid UK postcode."));
    billingPage.deliveryFormErrors().shouldHave(text("House number/name is required."));
    billingPage.deliveryFormErrors().shouldHave(text("City is required."));
    billingPage.deliveryFormErrors().shouldHave(text("Please use a valid UK phone number."));
  }

  private void checkMessagesOnAboutYouPage(AboutYouPage aboutYouPage) {
    aboutYouPage.goToBilling("", "");
    aboutYouPage.getErrorMessagesBox().shouldHave(text("First name is required."));
    aboutYouPage.getErrorMessagesBox().shouldHave(text("Last name is required."));
    aboutYouPage.getErrorMessagesBox().shouldHave(text("Password is required."));
    aboutYouPage.getErrorMessagesBox().shouldHave(text("Password confirm is required."));

    aboutYouPage.getDontCreateAccountCheckbox().click();
    assertTrue(aboutYouPage.getDontCreateAccountCheckbox().isSelected());
    aboutYouPage.getCreateAccountForm().shouldNotBe(visible);

    aboutYouPage.getReceiveDiscountsCheckbox().click();
    assertFalse(aboutYouPage.getReceiveDiscountsCheckbox().isSelected());
  }
}
