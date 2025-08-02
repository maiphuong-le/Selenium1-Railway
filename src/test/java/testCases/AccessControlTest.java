package testCases;

import Config.ExtentManager;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Constant.Constant;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccessControlTest {

    ExtentTest extentTest;
    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();

        homePage = new HomePage();
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
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
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC04() {
        extentTest = ExtentManager.createTest("TC04", "Login page displays when unlogged user accesses protected page");
        homePage.open();

        Constant.WEBDRIVER.findElement(By.xpath("//a[@href='/Page/BookTicketPage.cshtml']")).click();

        String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("Login"),
                "User is not redirected to Login page when accessing Book Ticket without logging in");
    }

    @Test
    public void TC06() {
        extentTest = ExtentManager.createTest("TC06", "Additional pages display once user logged in");
        homePage.open();

        loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

        WebElement tabMyTicket = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/Page/ManageTicket.cshtml']")));
        WebElement tabChangePassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/Account/ChangePassword.cshtml']")));
        WebElement tabLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/Account/Logout']")));

        Assert.assertTrue(tabMyTicket.isDisplayed(), "\"My ticket\" tab is not displayed after login");
        Assert.assertTrue(tabChangePassword.isDisplayed(), "\"Change password\" tab is not displayed after login");
        Assert.assertTrue(tabLogout.isDisplayed(), "\"Logout\" tab is not displayed after login");

        tabMyTicket.click();
        String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("ManageTicket"), "User is not navigated to My ticket page");

        Constant.WEBDRIVER.navigate().back();

        tabChangePassword = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/Account/ChangePassword.cshtml']")));
        tabChangePassword.click();
        currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("ChangePassword"), "User is not navigated to Change password page");
    }

}
