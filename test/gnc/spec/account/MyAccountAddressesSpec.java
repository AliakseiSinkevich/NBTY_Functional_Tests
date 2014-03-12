package gnc.spec.account;

import com.codeborne.selenide.Configuration;
import gnc.pages.ProductDetailsPage;
import gnc.pages.checkout.BillingPageRegistered;
import gnc.spec.GNCCommonSpec;
import org.junit.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static gnc.util.PageUtils.addProductToBasket;
import static gnc.util.PageUtils.registerUser;

public class MyAccountAddressesSpec extends GNCCommonSpec {
  @Test
  public void firstAppliedDeliveryAddressShouldBeMarkedAsDefault() throws Exception {
    registerUser("FirstName", "LastName");

    goToCheckoutAndCreateDeliveryAddress();

    open("/account/overview");
    $("span.default").shouldBe(visible);
  }

  private void goToCheckoutAndCreateDeliveryAddress() {
    ProductDetailsPage pdp = addProductToBasket("60003907");
    pdp.goToBasket().goToCheckout();

    BillingPageRegistered billingPage = page(BillingPageRegistered.class);
    billingPage.addDeliveryAddress("First Name", "Last Name", "W1T 3EF", "Street1", "City", "07031896976");
    billingPage.selectDeliveryAddress();
  }
}
