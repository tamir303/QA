package HW3.pages;

import HW3.AddToCartButtonTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AbstractTest {
    protected WebDriver driver;
    protected Map<String, Object> vars;
    protected JavascriptExecutor js;
    protected Logger logger;
    protected HomePage homePage;
    protected ShopPage shopPage;
    protected ProductPage productPage;

    @Before
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\97252\\Dropbox\\PC\\" + "\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        logger = LogManager.getLogger(AddToCartButtonTest.class);
        homePage = new HomePage(this.driver, "https://atid.store/", this.logger);
        shopPage = new ShopPage(this.driver, "https://atid.store/store/", this.logger);
        productPage = new ProductPage(this.driver, null, this.logger);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
