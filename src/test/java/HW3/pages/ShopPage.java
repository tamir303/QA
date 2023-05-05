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

    public void openShopPage(String menuItem) throws InterruptedException {
        driver.findElement(By.cssSelector("#" + menuItem + " > a")).click();
        HandleLogger("https://atid.store/store/", "Shop");
    }

    private List<WebElement> getProductContainer() {
         return driver.findElements(By.xpath("//*[@id='main']/div/ul/li"));
    }

    private String getProductName(String text)
    {
        return text.split("\n")[0];
    }

    public String getToItembyXpath(int XpathIndex) throws InterruptedException {
        List<WebElement> webElements = this.getProductContainer();
        if(XpathIndex < 0 || XpathIndex > webElements.size() ) throw new InterruptedException();
        webElements.get(XpathIndex).click();
        HandleLogger(driver.getCurrentUrl(), "Product");
        return driver.getCurrentUrl();
    }

    public void sortByHighToLow() throws InterruptedException {
        WebElement productContainer = driver.findElement(By.cssSelector("#main > div"));
        WebElement filterBar = productContainer.findElement(By.cssSelector("#main > div > form > select"));
        filterBar.sendKeys("Sort by price: high to low");
        logger.info(Testing.TestSuccess.toString("Sorted All Products By Price"));
    }

    public void searchProductByName(String product) throws InterruptedException {
        WebElement textField = driver.findElement(By.cssSelector("#wc-block-search__input-1"));
        textField.sendKeys(product);
        driver.findElement(By.cssSelector("#block-7 > div > form > div > button")).click();
        HandleLogger("https://atid.store/?s="+product+"&post_type=product", "Product("+product+")");
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
