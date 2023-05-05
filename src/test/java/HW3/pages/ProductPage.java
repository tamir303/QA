package HW3.pages;

import HW3.Testing;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends AbstractPageElement{
    private String productName;
    public ProductPage(WebDriver driver, String url, Logger logger) {
        super(driver, url, logger);
    }

    public void addProduct() throws InterruptedException {
        driver.findElement(By.className("single_add_to_cart_button")).click();
        logger.info(Testing.TestSuccess.toString(" Product was successfully added to Cart"));
        this.productName = driver.findElement(By.className("product_title")).getText();
        checkProductInCart();
    }

    public void checkProductInCart() throws InterruptedException {
        HandleLogger(driver.findElement(By.className("count")).getText()
                , "Product");
    }

    public void removeProduct() throws InterruptedException {
       new WebDriverWait(driver, 100).until(ExpectedConditions.visibilityOfElementLocated(
               By.xpath("//*[@id=\"main\"]/div/div[1]/div/a"))).click();
       driver.findElement(By.className("remove")).click();
        checkProductRemoved();
    }

    public void checkProductRemoved() throws InterruptedException {
        // Verify that the product has been removed from the cart
        WebElement messageElement = new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//p[@class='cart-empty woocommerce-info']")));
        if (!messageElement.getText().contains("Your cart is currently empty")) {
            Exception e = new InterruptedException("Product wasn't removed from cart");
            logger.info(Testing.TestFail.toString(e.getLocalizedMessage()));
            throw new InterruptedException();
        }
        logger.info(Testing.TestSuccess.toString("") + " Product successfully removed to cart");

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    protected void HandleLogger(String expectedValue, String checkType) throws InterruptedException {
        if (Integer.parseInt(expectedValue) == 1) {
            logger.info(Testing.TestSuccess.toString(productName + " was added to the cart."));
        } else {
            InterruptedException e = new InterruptedException("Failed to access");
            logger.info(Testing.TestFail.toString(productName + " was not added to the cart."));
            throw new InterruptedException();
        }
    }
}
