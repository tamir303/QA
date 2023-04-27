package HW3;

//Generated by Selenium IDE

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindHighestPriceProductTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    private JavascriptExecutor js;
    private Logger logger;

    @Before
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\97252\\Dropbox\\PC\\" + "\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        logger = LogManager.getLogger(AddToCartButtonTest.class);
    }

    @After
    public void tearDown() {
        //driver.quit();
    }

    @Test
    public void FindHighestPrice() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1052, 666));
        driver.get("https://atid.store/");
        HandelLogger("https://atid.store/", "Website");

        WebElement shop = driver.findElement(By.cssSelector("#menu-item-45 > a"));
        shop.click();
        HandelLogger("https://atid.store/store/", "Shop");

        WebElement productContainer = driver.findElement(By.cssSelector("#main > div"));
        WebElement filterBar = productContainer.findElement(By.cssSelector("#main > div > form > select"));
        filterBar.sendKeys("Sort by price: high to low");
        logger.info(Testing.TestSuccess.toString("Sorted All Products By Price"));

        productContainer = driver.findElement(By.cssSelector("#main > div"));
        WebElement product = productContainer.findElements(By.tagName("li")).get(0);
        product.click();
        HandelLogger("https://atid.store/product/anchor-bracelet/", "Highest Price Product");

        logger.info("Test {%s} Completed Successfully".formatted(this.getClass().getCanonicalName()));
        js.executeScript("window.scrollTo(0,1500)");
    }

    private void HandelLogger(String expectedUrl, String checkType) throws InterruptedException {
        if (driver.getCurrentUrl().equals(expectedUrl))
            logger.info(Testing.TestSuccess.toString(checkType) + " Successfully accessed");
        else {
            InterruptedException e = new InterruptedException("Failed to access");
            logger.info(Testing.TestFail.toString("checkType") + e.getLocalizedMessage());
            throw new InterruptedException();
        }
    }

    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        org.junit.runner.Result result = junit.run(AddToCartButtonTest.class); // Replace "SampleTest" with the name of your class

        if (result.getFailureCount() > 0) {
            System.out.println("Test failed.");
            System.exit(1);
        } else {
            System.out.println("Test finished successfully.");
            System.exit(0);
        }
    }
}
