package testCases;

import Common.DriverManager;
import Config.ExtentManager;
import Constant.Constant;
import Model.Account;
import messages.Message;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.RegisterPage;
import PageObjects.ChangePasswordPage;
import config.BaseTest;
import dataTest.DataTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangePasswordTest extends BaseTest {

    private final HomePage homePage = new HomePage();
    private final RegisterPage registerPage = new RegisterPage();
    private final LoginPage loginPage = new LoginPage();
    private final ChangePasswordPage changePasswordPage = new ChangePasswordPage();

    @Test(description = "TC09 - User can change password",
            dataProvider = "change_password_data",
            dataProviderClass = DataTests.class)
    public void TC09(Account account, String newPassword, String confirmPassword) {
        extentTest = ExtentManager.createTest("ChangePasswordTest: TC09", "User can change password");

        homePage.open();

        homePage.clickRegisterTab();
        registerPage.registerConfirm(account);

        homePage.gotoLoginPage();
        loginPage.login(account.getEmail(), account.getPassword());

        homePage.gotoChangePasswordPage();

        changePasswordPage.changePassword(account.getPassword(), newPassword, confirmPassword);

        Assert.assertEquals(
                changePasswordPage.getSuccessMessage(),
                Message.CHANGE_PASSWORD_SUCCESS,
                Message.ERROR_CHANGE_PASSWORD_NOT_DISPLAYED
        );

    }
}
