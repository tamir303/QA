package HW3.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPageElement {
    protected WebDriver driver;
    protected String url;
    protected Logger logger;

    public AbstractPageElement(WebDriver driver, String url, Logger logger) {
        this.driver = driver;
        this.url = url;
        this.logger = logger;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    protected abstract void HandleLogger(String expectedUrl, String checkType) throws InterruptedException;

    @Override
    public String toString() {
        return "AbstractWebElement{" +
                "driver=" + driver +
                ", url='" + url + '\'' +
                ", logger=" + logger +
                '}';
    }
}
