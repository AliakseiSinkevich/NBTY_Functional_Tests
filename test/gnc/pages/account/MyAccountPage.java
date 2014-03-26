package gnc.pages.account;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MyAccountPage {
  public void updateHomeAddress(String phone, String postcode, String address1, String city) {
    $(byText("Edit details")).click();

    $("#frm_registration_phone").val(phone);
    $("#postcode-lookup").val(postcode);
    $("#frm_address_housenumber").val(address1);
    $("#frm_address_town").val(city);

    $("#register-submit").click();
  }
}
