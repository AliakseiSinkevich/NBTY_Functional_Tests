package gnc.pages;

import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage {
  public void register(String firstName, String lastName, String email, String password) {
    $("#first-name").val(firstName);
    $("#last-name").val(lastName);
    $("#email-addr").val(email);
    $("#email-confirm").val(email);
    $("#frm_registration_password").val(password);
    $("#frm_registration_password_confirm").val(password);

    $("#register-submit").click();
  }
}
