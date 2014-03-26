package gnc.spec;

import gnc.pages.HomePage;
import gnc.pages.RegistrationPage;
import gnc.pages.SignInPage;
import org.junit.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static gnc.util.PageUtils.signOut;

public class RegistrationSpec extends GNCCommonSpec {
  @Test
  public void userCanRegister() {
    signOut();
    RegistrationPage registrationPage = open("/", HomePage.class).openSignInPage().openRegistrationPage();
    String email = "selenide-test@" + System.currentTimeMillis() + "gmail.com";
    String password = "123456";
    registrationPage.register("Aliaksandr", "Lukashenko", email, password);

    $("#usernav").shouldHave(text("Aliaksandr Lukashenko"));
    signOut();
    open("/account/login", SignInPage.class).signIn(email, password);
    $("#usernav").shouldHave(text("Aliaksandr Lukashenko"));
  }
}
