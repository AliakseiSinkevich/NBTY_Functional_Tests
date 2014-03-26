package gnc.spec.mygnc;

import gnc.model.MyGncWizard;
import gnc.pages.account.AccountEditDetailsPage;
import gnc.spec.GNCCommonSpec;
import gnc.util.PageUtils;
import org.junit.Before;
import org.junit.Test;
import util.PropertiesManager;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static gnc.util.PageUtils.signOut;
import static org.junit.Assert.assertEquals;
import static gnc.util.PageUtils.clearCookie;
import static gnc.util.PageUtils.getCookieByName;
import static gnc.util.PageUtils.setCookie;

/**
 * Tests are not stable because depend on ajax loads. May require rerun to pass.
 */
public class WizardSpec extends GNCCommonSpec {

  @Before
  public void setUp() throws Exception {
    signOut();
    open("/");
    clearWizardCookies();
  }

  @Test
  public void checkBasicWizardFlow() throws Exception {
    selectWizardChoices("Male", "Healthy Living", "Cycling", "Medium", "I'm a beginner");

    assertEquals(PropertiesManager.getGNCBaseUrl() + "/training/overview", getWebDriver().getCurrentUrl());
  }

  @Test
  public void checkMergePrefsFromCookiesToDB() throws Exception {
    selectWizardChoices("Male", "Healthy Living");

    PageUtils.registerUser("first name", "last name");
    open("/");
    new MyGncWizard().startWizard();
    $("#mergeWizardProps").shouldBe(visible);
    $("#mergeGncPrefsLink").click();


    AccountEditDetailsPage accountDetailsPage = open("/account/editdetails", AccountEditDetailsPage.class);
    assertEquals("male", accountDetailsPage.getSelectedGender());
    assertEquals("Healthy Living", accountDetailsPage.getSelectedFitnessGoal());
    assertEquals("Not completed", accountDetailsPage.getSelectedSport());
    assertEquals("Not completed", accountDetailsPage.getSelectedBodyType());
  }

  @Test
  public void checkMergePrefsFromDBToCookies() throws Exception {
    String login = PageUtils.registerUser("first name", "last name");
    selectWizardChoices("Male", "Healthy Living", "Cycling", "Medium", "I'm a beginner");
    signOut();
    clearWizardCookies();
    setCookie("wizardChoicesTrail", "someNotEmptyValue");

    PageUtils.logIn(login);
    open("/");
    new MyGncWizard().startWizard();
    $("#mergeWizardProps").shouldBe(visible);
    $("#dontMergeGncPrefsLink").click();

    assertEquals("maleGenderChoice,maleGeneralFitnessChoice,maleCyclingChoice,maleMediumChoice,beginnerChoice", getCookieByName("wizardChoicesTrail").getValue());
    assertEquals("male", getCookieByName("gender").getValue());
    assertEquals("HealthyLiving", getCookieByName("fitnessGoal").getValue());
    assertEquals("cycling", getCookieByName("sport").getValue());
    assertEquals("medium", getCookieByName("bodyType").getValue());
    assertEquals("chBeginner", getCookieByName("challenge").getValue());
  }

  private void selectWizardChoices(String... choicesNames) {
    open("/");
    MyGncWizard myGNC = new MyGncWizard();
    myGNC.startWizard();
    for (String choiceName : choicesNames) {
      myGNC.selectWizardOption(choiceName);
    }
  }

  private void clearWizardCookies() {
    clearCookie("wizardChoicesTrail");
    clearCookie("gender");
    clearCookie("fitnessGoal");
    clearCookie("sport");
    clearCookie("bodyType");
    clearCookie("challenge");
    refresh();
  }
}
