package Steps;

import Base.Func;
import Utilities.Log4j;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AddToCart extends Func {
    public static WebDriver driver = new EdgeDriver();
    public static JavascriptExecutor js = (JavascriptExecutor) driver;

    @Given("a web browser is at the home page")
    public void aWebBrowserIsAtTheHomePage() {
        Func.start(driver);
        Log4j.startLog("AddToCart");
    }

    @When("user write spoon food to the search field and enter")
    public void userWriteSpoonFoodToTheSearchFieldAndEnter() {
        driver.findElement(By.id("txtSearchBox")).click();
        driver.findElement(By.id("txtSearchBox")).sendKeys("kaşık maması");
        driver.findElement(By.id("txtSearchBox")).sendKeys(Keys.ENTER);
        Log4j.info("Arama yapıldı.");
    }

    @And("user click the last item and verify it")
    public void userClickTheLastItemAndVerifyIt() {
        Func.sleep(4);
        js.executeScript("document.getElementById(`chkStoktakiler`).click();");
        try {
            for (int i = 0; i < 6; i++) {
                Thread.sleep(2000);
                js.executeScript("window.scrollTo(0,425*(i+1))");
            }
        } catch (Exception e) {//
        }
        Func.sleep(2);
        String textLastItem = driver.findElement(By.xpath("//eb-product-list-item[last()]/div/eb-generic-link/a/div/h2/span")).getText();
        Log4j.info("Son eşyanın adı:"+textLastItem);
        driver.findElement(By.xpath("//eb-product-list-item[last()]")).click();
        Func.sleep(2);
        js.executeScript("window.scrollTo(0,383.20001220703125)");
        String textProductInfo = driver.findElement(By.cssSelector(".ng-star-inserted:nth-child(1) > .ng-star-inserted > .product-info #txtProductTitle")).getText();
        Log4j.info("Açılan eşyanın adı:"+textProductInfo);
        assertThat(textLastItem,is(textProductInfo));
        Log4j.info("Eşya ismi doğrulaması başarılı.");
    }

    @And("user add the item to the cart")
    public void userAddTheItemToTheCart() {
        js.executeScript("document.getElementById(`addToCartBtn`).click();");
        Log4j.info("Eşya sepete eklendi.");
    }

    @And("user go to the cart")
    public void userGoToTheCart() {
        Func.sleep(2);
        js.executeScript("document.getElementById(`btnShowCart`).click();");
        Log4j.info("Sepete gidildi.");

    }

    @And("user completes the transaction")
    public void userCompletesTheTransaction() {
        Func.sleep(2);
        js.executeScript("window.scrollTo(0,72.80000305175781)");
        Func.sleep(2);
        driver.findElement(By.id("btnGoToShippingAddress")).click();
        Log4j.info("Satın al butonuna basıldı.");
    }

    @Then("login page opens")
    public void loginPageOpens() {
        Func.sleep(2);
        assertThat(driver.findElement(By.cssSelector(".title-primary")).getText(), is("Üyelik"));
        Log4j.info("Üye girişine yönlendirme başarıyla gerçekleşti.");
    }

    @And("the driver closes")
    public void theDriverCloses() {
        Log4j.endLog("AddToCart");
        driver.quit();
    }
}
