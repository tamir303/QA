package HW3.pages;

import freemarker.template.TemplateException;
import io.github.sridharbandi.HtmlCsRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class AbstractTest {
    protected WebDriver driver;
    protected Map<String, Object> vars;
    protected JavascriptExecutor js;
    protected static HtmlCsRunner htmlCsRunner;
    protected Logger logger;
    protected HomePage homePage;
    protected ShopPage shopPage;
    protected ProductPage productPage;
    protected JSONObject base_config;

    @Before
    public void setUp() throws IOException {
        logger = LogManager.getLogger(this.getClass());
        base_config =readJSON("src/test/java/HW3/jsons/config.json");
        String driver_path = (String) base_config.get("driverPath");
        System.setProperty("webdriver.chrome.driver", driver_path);
        driver = new ChromeDriver();
        htmlCsRunner = new HtmlCsRunner(driver);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        homePage = new HomePage(this.driver, "https://atid.store/", this.logger);
        shopPage = new ShopPage(this.driver, "https://atid.store/store/", this.logger);
        productPage = new ProductPage(this.driver, null, this.logger);
    }
    public JSONObject readJSON(String path) {
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader reader;
            reader = new FileReader(path);
            // Read JSON file and return as JSONArray
            return (JSONObject)jsonParser.parse(reader);
        } catch (ParseException | IOException e) {
            logger.error("Reading JSON Failed: %s".formatted(e.getMessage()));
            return null;
        }
    }
    @After
    public void tearDown() throws TemplateException, IOException, URISyntaxException {
        htmlCsRunner.execute();
        driver.quit();
        htmlCsRunner.generateHtmlReport();
    }
}
