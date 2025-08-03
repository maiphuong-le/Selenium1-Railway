package testCases;

import Config.ExtentManager;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import config.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Constant.Constant;
import messages.Message;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {

    HomePage homePage;
    LoginPage loginPage;
    SoftAssert softAssert;


    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        softAssert = new SoftAssert();

    }


    @Test
    public void TC01() {
        extentTest = ExtentManager.createTest("TC01", "User can log into Railway with valid username and password");
        homePage.open();
        loginPage = homePage.gotoLoginPage();

        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = Message.WELCOME_MESSAGE + Constant.USERNAME;
        Assert.assertEquals(actualMsg, expectedMsg, Message.WELCOME_MESSAGE_NOT_DISPLAYED);

        softAssert.assertAll();
    }

    @Test
    public void TC02() {
        extentTest = ExtentManager.createTest("TC02", "User can't login with blank 'Username' textbox");
        homePage.open();
        loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.BlANK_USERNAME, Constant.PASSWORD);
        Assert.assertEquals(loginPage.getLoginErrorMessageText(), Message.LOGIN_ERROR, Message.ERROR_USERNAME_BLANK);
        softAssert.assertAll();
    }

    @Test
    public void TC03() {
        extentTest = ExtentManager.createTest("TC03", "User cannot log into Railway with invalid password");
        homePage.open();
        loginPage = homePage.gotoLoginPage();

        loginPage.login(Constant.USERNAME, Constant.INVALID_PASSWORD);
        Assert.assertEquals(loginPage.getLoginErrorMessageText(), Message.LOGIN_ERROR, Message.ERROR_PASSWORD_INVALID);

        softAssert.assertAll();
    }

    @Test
    public void TC05() {
        extentTest = ExtentManager.createTest("TC05", "System shows message when user enters wrong password several times");
        homePage.open();

        loginPage = homePage.gotoLoginPage();
        loginPage.loginWithInvalidAttempts(Constant.USERNAME, Constant.INVALID_PASSWORD, 4);

        Assert.assertEquals(loginPage.getLoginErrorMessageText(), Message.WARNING_LOGIN_ATTEMPTS, Message.WARNING_LOGIN_ATTEMPTS_NOT_DISPLAYED);

        softAssert.assertAll();
    }
}