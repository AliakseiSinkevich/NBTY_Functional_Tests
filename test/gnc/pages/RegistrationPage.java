package gnc.pages;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
  public void register(String firstName, String lastName, String email, String password) {
    $("#frm_registration_firstname").val(firstName);
    $("#frm_registration_lastname").val(lastName);
    $("#frm_registration_email").val(email);
    $("#frm_registration_email_confirm").val(email);
    $("#frm_registration_password").val(password);
    $("#frm_registration_password_confirm").val(password);

    $("#register-submit").click();
  }
}
