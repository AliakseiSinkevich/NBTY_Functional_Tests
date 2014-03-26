package gnc.model;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;

public class MyGncWizard {
  public void startWizard() {
    $(".wizardstart").click();
  }

  public void selectWizardOption(String optionName) {
    sleep(500);
    $$(".option").findBy(text(optionName)).click();
  }
}
