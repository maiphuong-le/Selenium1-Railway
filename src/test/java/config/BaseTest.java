package config;

import Config.ExtentManager;
import Constant.Constant;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.ITestResult;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.HashMap;

public class BaseTest {
    protected ExtentTest extentTest;

    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);

        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-info-bars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-blink-features=PasswordLeakDetection");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-autofill-keyboard-accessory-view");
        options.addArguments("--disable-password-generation");
        options.addArguments("--disable-translate");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--no-first-run");
        options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/chrome-profile-" + System.currentTimeMillis());

        Constant.WEBDRIVER = new ChromeDriver(options);
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (extentTest != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                extentTest.fail("Test failed: " + result.getThrowable().getMessage());
                String screenshotPath = ExtentManager.captureScreenshot(Constant.WEBDRIVER, result.getName());
                extentTest.addScreenCaptureFromPath(screenshotPath);
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest.pass("Test passed");
            } else if (result.getStatus() == ITestResult.SKIP) {
                extentTest.skip("Test skipped: " + result.getThrowable().getMessage());
            }
            ExtentManager.flush();
        }
        Constant.WEBDRIVER.quit();
    }
}