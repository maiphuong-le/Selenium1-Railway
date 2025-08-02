package testCases;

import Config.ExtentManager;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Constant.Constant;

public class LoginTest {

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
    public void TC01() {
        extentTest = ExtentManager.createTest("TC01", "User can log into Railway with valid username and password");

        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;

        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {

        extentTest = ExtentManager.createTest("TC02", "User can't login with blank 'Username' textbox");
        homePage.open();

        loginPage = homePage.gotoLoginPage();

        loginPage.login("", Constant.PASSWORD);

        String actualMsg = loginPage.getLoginErrorMessageText();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected when username is blank");
    }

    @Test
    public void TC03() {

        extentTest = ExtentManager.createTest("TC03", "User cannot log into Railway with invalid password");
        homePage.open();

        loginPage = homePage.gotoLoginPage();

        loginPage.login(Constant.USERNAME, "invalidPassword123");

        String actualMsg = loginPage.getLoginErrorMessageText();
        String expectedMsg = "There was a problem with your login and/or errors exist in your form.";

        Assert.assertEquals(actualMsg, expectedMsg, "Error message is not displayed as expected when password is invalid");
    }

    @Test
    public void TC05() {
        extentTest = ExtentManager.createTest("TC05", "System shows message when user enters wrong password several times");

        homePage.open();

        loginPage = homePage.gotoLoginPage();

        for (int i = 1; i <= 4; i++) {
            System.out.println("Attempt " + i);
            loginPage.getTxtUsername().clear();
            loginPage.getTxtPassword().clear();
            loginPage.login(Constant.USERNAME, "invalidPassword123");

            if (i < 4) {
                String generalError = loginPage.getLoginErrorMessageText();
                Assert.assertTrue(generalError.contains("There was a problem"), "General login error message should appear");
            }
        }

        String warningMsg = loginPage.getLoginErrorMessageText();
        String expectedMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        Assert.assertEquals(warningMsg, expectedMsg, "Warning message for multiple failed login attempts is not displayed as expected");
    }


}
