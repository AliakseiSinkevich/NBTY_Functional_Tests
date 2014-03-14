package gnc.spec;

import gnc.pages.HomePage;
import gnc.pages.RegistrationPage;
import gnc.pages.SignInPage;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static gnc.util.PageUtils.signOut;

public class RegistrationSpec extends GNCCommonSpec {
  @Test
  @Ignore
  public void userCanRegister() {
    signOut();
    RegistrationPage registrationPage = open("/", HomePage.class).openSignInPage().openRegistrationPage();
    String email = "selenide-test@" + new Random().nextDouble() + "gmail.com";
    String password = "123456";
    registrationPage.register("Aliaksandr", "Lukashenko", email, password);

    $(".signin").shouldHave(text("Aliaksandr Lukashenko"));
    signOut();
    open("/account/login", SignInPage.class).signIn(email, password);
    $(".signin").find("a").shouldHave(text("Aliaksandr Lukashenko"));
  }
}
