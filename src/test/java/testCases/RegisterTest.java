package testCases;

import Config.ExtentManager;
import Constant.Constant;
import Model.Account;
import PageObjects.HomePage;
import PageObjects.RegisterPage;
import dataTest.DataTests;
import messages.Message;
import config.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegisterTest extends BaseTest {

    HomePage homePage;
    RegisterPage registerPage;
    SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        registerPage = new RegisterPage();
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "validRegisterAccount", dataProviderClass = DataTests.class)
    public void TC07(Account account) {
        extentTest = ExtentManager.createTest("TC07", "User can create new account");
        homePage.open();
        homePage.clickRegisterTab();

        registerPage.register(
                account.getEmail(),
                account.getPassword(),
                account.getConfirmPassword(),
                account.getPid()
        );

        Assert.assertEquals(
                registerPage.getSuccessMessage(),
                Message.REGISTRATION_SUCCESS,
                "Success message is not displayed as expected"
        );

        softAssert.assertAll();
    }



    @Test(dataProvider = "invalidConfirmPasswordAccount", dataProviderClass = DataTests.class)
    public void TC10(Account account) {
        extentTest = ExtentManager.createTest("TC10", "User can't create account with mismatched confirm password");

        homePage.open();
        homePage.clickRegisterTab();

        registerPage.register(
                account.getEmail(),
                account.getPassword(),
                account.getConfirmPassword(),
                account.getPid()
        );

        Assert.assertEquals(
                registerPage.getErrorMessage(),
                Message.FORM_ERROR,
                "Error message is not displayed as expected"
        );

        softAssert.assertAll();
    }

    @Test(dataProvider = "emptyPasswordAndPid", dataProviderClass = DataTests.class)
    public void TC11(Account account) {
        extentTest = ExtentManager.createTest("TC11", "User can't create account while password and PID fields are empty");

        homePage.open();
        homePage.clickRegisterTab();

        registerPage.register(
                account.getEmail(),
                account.getPassword(),
                account.getConfirmPassword(),
                account.getPid()
        );

        Assert.assertEquals(registerPage.getErrorMessage(), Message.FORM_ERROR);
        Assert.assertEquals(registerPage.getFieldErrorMessage("password"), Message.INVALID_PASSWORD_LENGTH);
        Assert.assertEquals(registerPage.getFieldErrorMessage("pid"), Message.INVALID_ID_LENGTH);

        softAssert.assertAll();
    }
}
