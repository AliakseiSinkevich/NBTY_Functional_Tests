package gnc.pages.account;

import static com.codeborne.selenide.Selenide.$;

public class AccountEditDetailsPage {
  public String getSelectedGender() {
    return $("div.rightLabel:nth-child(1) > span:nth-child(2)").getText().trim();
  }

  public String getSelectedFitnessGoal() {
    return $("div.rightLabel:nth-child(2) > span:nth-child(2)").getText().trim();
  }

  public String getSelectedSport() {
    return $("div.rightLabel:nth-child(3) > span:nth-child(2)").getText().trim();
  }

  public String getSelectedBodyType() {
    return $("div.rightLabel:nth-child(4) > span:nth-child(2)").getText().trim();
  }
}
