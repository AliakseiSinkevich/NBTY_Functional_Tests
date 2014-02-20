package gnc.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SignInPage {
  public void signIn(String login, String password) {
    $("#frm_login_email").val(login);
    $("#frm_login_password").val(password);
    $(".loginActions").find(byText("Sign in")).click();
  }

  public RegistrationPage openRegistrationPage() {
    $(".register").find("a").click();
    return page(RegistrationPage.class);
  }
}
