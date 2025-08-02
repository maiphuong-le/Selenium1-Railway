package testCases;

import Config.ExtentManager;
import Constant.Constant;
import PageObjects.HomePage;
import PageObjects.RegisterPage;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class RegisterTest {

    ExtentTest extentTest;
    HomePage homePage;
    RegisterPage registerPage;

    @BeforeMethod
    public void beforeMethod() {
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
        homePage = new HomePage();
        registerPage = new RegisterPage();
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
    public void TC07() {
        extentTest = ExtentManager.createTest("TC07", "User can create new account");

        String email = Constant.generateUniqueEmail();
        String password = Constant.VALID_PASSWORD;
        String confirmPassword = Constant.VALID_PASSWORD;
        String pid = Constant.VALID_PID;

        homePage.open();
        Constant.WEBDRIVER.findElement(By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']")).click();
        registerPage.register(email, password, confirmPassword, pid);

        String actualMsg = registerPage.getSuccessMessage();
        String expectedMsg = "Registration Confirmed! You can now log in to the site.";
        Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");
    }

    @Test
    public void TC10() {
        extentTest = ExtentManager.createTest("TC10", "User can't create account with mismatched confirm password");

        String email = Constant.generateUniqueEmail();
        String password = Constant.VALID_PASSWORD;
        String confirmPassword = "Different123"; // intentionally wrong
        String pid = Constant.VALID_PID;


        homePage.open();
        Constant.WEBDRIVER.findElement(By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']")).click();
        registerPage.register(email, password, confirmPassword, pid);

        String actualMsg = registerPage.getErrorMessage();
        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC11() {
        extentTest = ExtentManager.createTest("TC11", "User can't create account while password and PID fields are empty");

        String email = "test" + System.currentTimeMillis() + "@example.com";
        String password = "";
        String confirmPassword = "";
        String pid = "";

        homePage.open();
        Constant.WEBDRIVER.findElement(By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']")).click();
        registerPage.register(email, password, confirmPassword, pid);

        String actualFormError = registerPage.getErrorMessage();
        String actualPasswordError = registerPage.getFieldErrorMessage("password");
        String actualPidError = registerPage.getFieldErrorMessage("pid");

        Assert.assertEquals(actualFormError, "There're errors in the form. Please correct the errors and try again.");
        Assert.assertEquals(actualPasswordError, "Invalid password length");
        Assert.assertEquals(actualPidError, "Invalid ID length");
    }





}
