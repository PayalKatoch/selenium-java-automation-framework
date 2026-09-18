package listeners;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;


public class TestListener implements ITestListener {

    private static final Logger log =
            LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {

        BaseTest baseTest = (BaseTest) result.getInstance();
        WebDriver driver = baseTest.getDriver();
        String testName = result.getName();

        log.error("Test failed: {}", testName, result.getThrowable());

        if (driver != null) {
            ScreenshotUtils.captureScreenshot(driver, testName);
            ScreenshotUtils.attachScreenshotToAllure(driver, testName);

            log.info("Failure screenshot captured and attached for: {}", testName);
        } else {
            log.warn("Screenshot not captured because WebDriver is null for: {}", testName);
        }
    }
}