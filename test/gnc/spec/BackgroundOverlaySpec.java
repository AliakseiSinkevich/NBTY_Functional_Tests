package gnc.spec;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertEquals;

public class BackgroundOverlaySpec extends GNCCommonSpec {
  @Test
  public void overlayShouldBeUniqueOnEachPage() throws Exception {
    open("/");
    checkBackgroundOverlayIsUnique();

    open("/comparator");
    checkBackgroundOverlayIsUnique();

    open("/shop/Food-Drink");
    checkBackgroundOverlayIsUnique();

    open("/shop/product/Zero-Noodles-60003907");
    checkBackgroundOverlayIsUnique();

    open("/blog");
    checkBackgroundOverlayIsUnique();
  }

  private void checkBackgroundOverlayIsUnique() {
    assertEquals(1, $$(".overlaybg").size());
    assertEquals(1, $$(".overlay").size());
  }
}
