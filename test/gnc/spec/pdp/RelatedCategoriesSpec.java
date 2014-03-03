package gnc.spec.pdp;

import com.codeborne.selenide.SelenideElement;
import gnc.pages.ProductDetailsPage;
import gnc.spec.GNCCommonSpec;
import org.junit.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static gnc.util.PageUtils.getProductUrl;
import static org.junit.Assert.assertTrue;

public class RelatedCategoriesSpec extends GNCCommonSpec {
  @Test
  public void verifyRelatedCategoriesLinksEndsWithSlash() throws Exception {
    ProductDetailsPage pdp = open(getProductUrl("60083511"), ProductDetailsPage.class);

    for (SelenideElement link : pdp.getRelatedCategoriesList().findAll("a")) {
      String href = link.getAttribute("href");
      assertTrue("Product type link " + link + " must be ended with slash", href.endsWith("/"));
      assertTrue("Product type url " + href + " should be combined as dimension selection url", href.contains("/shop/N=producttype_"));
    }

    pdp.getRelatedCategoriesList().find("a").click();
    $("#products").shouldBe(visible);
  }
}
