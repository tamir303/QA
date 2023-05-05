package HW3.pages;

import HW3.Testing;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPageElement {

    public HomePage(WebDriver driver, String url, Logger logger) {
        super(driver, url, logger);
    }

    public void openHomePage() throws InterruptedException {
        driver.manage().window().setSize(new Dimension(1052, 666));
        driver.get("https://atid.store/");
        HandleLogger("https://atid.store/", "Website");
    }

    @Override
    protected void HandleLogger(String expectedUrl, String checkType) throws InterruptedException {
        if (this.getDriver().getCurrentUrl().equals(expectedUrl))
            logger.info(Testing.TestSuccess.toString(checkType) + " Successfully accessed");
        else {
            InterruptedException e = new InterruptedException("Failed to access");
            logger.info(Testing.TestFail.toString("checkType") + e.getLocalizedMessage());
            throw new InterruptedException();
        }
    }

    public void openPage(String option) throws InterruptedException {
        driver.findElement(By.id(option)).click();
        HandleLogger("https://atid.store/store/", "Shop");
    }
}
