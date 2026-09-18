package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public WebDriver createBrowser(String browser) {

        if (browser.equalsIgnoreCase("Chrome")) {

            ChromeOptions options = new ChromeOptions();

            Map<String, Object> prefs = new HashMap<>();

            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);

            options.setExperimentalOption("prefs", prefs);

            // Headless only when explicitly requested
            boolean headless = Boolean.parseBoolean(
                    System.getProperty("headless", "false")
            );

            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }

            driver.set(new ChromeDriver(options));
            getDriver().manage().window().maximize();
        }

        else if (browser.equalsIgnoreCase("Firefox")) {

            driver.set(new FirefoxDriver());
        }

        else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        }
        return getDriver();
    }


    public static WebDriver getDriver() {
        return driver.get();
    }


    public static void quitDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            currentDriver.quit();
            driver.remove();
        }
    }
}