package gnc.spec;

import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.BeforeClass;
import org.junit.Rule;
import util.PropertiesManager;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.timeout;

public class GNCCommonSpec {
  @Rule
  public ScreenShooter screenShooter = ScreenShooter.failedTests();

  @BeforeClass
  public static void setup() {
    baseUrl = PropertiesManager.getGNCBaseUrl();
    timeout = PropertiesManager.getTimeout();
  }
}
