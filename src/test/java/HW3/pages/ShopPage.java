package HW3.pages;

import HW3.Testing;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShopPage extends AbstractPageElement {

    public ShopPage(WebDriver driver, String url, Logger logger) {
        super(driver, url, logger);
    }

    public void openShopPage() throws InterruptedException {
        driver.findElement(By.cssSelector("#menu-item-45 > a")).click();
        HandleLogger("https://atid.store/store/", "Shop");
    }

    public List<WebElement> getProductContainer() {
        return driver.findElement(By.cssSelector("#main > div")).findElements(By.tagName("li"));
    }

    public String getFirstItem() throws InterruptedException {
        this.getProductContainer().get(0).click();
        HandleLogger("https://atid.store/product/anchor-bracelet/", "Product");
        return driver.getCurrentUrl();
    }

    public void sortByHighToLow() throws InterruptedException {
        WebElement productContainer = driver.findElement(By.cssSelector("#main > div"));
        WebElement filterBar = productContainer.findElement(By.cssSelector("#main > div > form > select"));
        filterBar.sendKeys("Sort by price: high to low");
        logger.info(Testing.TestSuccess.toString("Sorted All Products By Price"));
    }

    @Override
    protected void HandleLogger(String expectedUrl, String checkType) throws InterruptedException {
        if (driver.getCurrentUrl().equals(expectedUrl))
            logger.info(Testing.TestSuccess.toString(checkType) + " Successfully accessed");
        else {
            InterruptedException e = new InterruptedException(" Failed to access");
            logger.info(Testing.TestFail.toString(checkType) + e.getLocalizedMessage());
            throw new InterruptedException();
        }
    }
}
