package base;

import config.PropertyReader;
import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    private static final Logger log =
            LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod(alwaysRun = true)
    public void setup() {

        PropertyReader config =
                new PropertyReader("config.properties");

        String browser = System.getProperty(
                "browser",
                config.getProperty("browser")
        );

        DriverFactory driverFactory = new DriverFactory();

        driver = driverFactory.createBrowser(browser);

        log.info(
                "Running tests on browser: {} | Thread: {}",
                browser,
                Thread.currentThread().getId()
        );

        driver.get(config.getProperty("baseUrl"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
        driver = null;
    }

    public WebDriver getDriver() {
        return driver;
    }
}