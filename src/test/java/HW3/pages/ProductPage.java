package HW3.pages;

import HW3.Testing;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends AbstractPageElement{
    private String productName;
    public ProductPage(WebDriver driver, String url, Logger logger) {
        super(driver, url, logger);
    }

    public void addProduct() throws InterruptedException {
        WebElement addProduct = driver.findElement(By.cssSelector("#product-160 > div.summary.entry-summary > form > button"));
        addProduct.click();
        logger.info(Testing.TestSuccess.toString(" Product was successfully added to Cart"));
        this.productName = driver.findElement(By.cssSelector("#product-160 > div.summary.entry-summary > h1")).getText();
        checkProductInCart();
    }

    public void checkProductInCart() throws InterruptedException {
        WebElement cartIcon = driver.findElement(By.cssSelector("#ast-site-header-cart > div.ast-site-header-cart-li > a > div > span"));
        HandleLogger(cartIcon.getText(), "Product");
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    protected void HandleLogger(String expectedUrl, String checkType) throws InterruptedException {
        if (Integer.parseInt(expectedUrl) == 1) {
            logger.info(Testing.TestSuccess.toString(productName + " was added to the cart."));
        } else {
            InterruptedException e = new InterruptedException("Failed to access");
            System.out.println(Testing.TestFail.toString(productName + " was not added to the cart."));
            throw new InterruptedException();
        }
    }
}
