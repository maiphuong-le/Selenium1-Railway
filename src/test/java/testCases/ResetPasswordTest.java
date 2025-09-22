package testCases;

import Config.EmailConfig;
import Config.ExtentManager;
import Common.MailReader;
import Common.UrlExtractor;
import Model.Account;
import PageObjects.*;
import dataTest.DataTests;
import messages.Message;
import config.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResetPasswordTest extends BaseTest {

    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    ForgotPasswordPage forgotPasswordPage;
    ResetPasswordPage resetPasswordPage;
    MailReader mailReader;
    EmailConfig emailConfig;

    @BeforeMethod
    public void setUp() {
        // Khởi tạo các đối tượng trước mỗi test
        homePage = new HomePage();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        forgotPasswordPage = new ForgotPasswordPage();
        resetPasswordPage = new ResetPasswordPage();
        mailReader = new MailReader();
        emailConfig = new EmailConfig();
    }

    @Test(dataProvider = "resetPasswordBlankToken", dataProviderClass = DataTests.class,
            description = "Errors display when password reset token is blank")
    public void TC12(Account account, String newPassword, String confirmPassword, String blankToken) {
        extentTest = ExtentManager.createTest("TC12", "Errors display when password reset token is blank");

        homePage.open();
        homePage.clickRegisterTab();
        registerPage.register(account.getEmail(), account.getPassword(), account.getConfirmPassword(), account.getPid());


        homePage.clickLoginTab();
        loginPage.clickForgotPasswordLink();

        forgotPasswordPage.requestPasswordReset(account.getEmail());

        String emailContent = mailReader.readEmail(emailConfig);
        if (emailContent == null || emailContent.isEmpty()) {
            throw new RuntimeException("Unable to read email content for password reset");
        }
        String resetUrl = UrlExtractor.confirmationUrlResetPassword(emailContent);
        if (resetUrl == null || resetUrl.isEmpty()) {
            throw new RuntimeException("Unable to extract reset URL from email");
        }
        resetPasswordPage.navigateTo(resetUrl);

        resetPasswordPage.fillResetPasswordForm(newPassword, confirmPassword);
        resetPasswordPage.clearResetToken();

        resetPasswordPage.clickResetPasswordButton();

        Assert.assertEquals(
                resetPasswordPage.getGeneralErrorMessage(),
                Message.RESET_TOKEN_INCORRECT_OR_EXPIRED);
        Assert.assertEquals(
                resetPasswordPage.getTokenErrorMessage(),
                Message.RESET_TOKEN_INVALID);
    }

    @Test(dataProvider = "resetPasswordMismatch", dataProviderClass = DataTests.class,
            description = "Errors display if password and confirm password don't match")
    public void TC13(Account account, String newPassword, String mismatchedConfirmPassword, String fakeToken) {
        extentTest = ExtentManager.createTest("TC13", "Errors display if password and confirm password don't match");

        homePage.open();
        homePage.clickRegisterTab();
        registerPage.register(account.getEmail(), account.getPassword(), account.getConfirmPassword(), account.getPid());

        homePage.clickLoginTab();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.requestPasswordReset(account.getEmail());
        String emailContent = mailReader.readEmail(emailConfig);
        if (emailContent == null || emailContent.isEmpty()) {
            throw new RuntimeException("Unable to read email content for password reset");
        }
        String resetUrl = UrlExtractor.confirmationUrlResetPassword(emailContent);
        if (resetUrl == null || resetUrl.isEmpty()) {
            throw new RuntimeException("Unable to extract reset URL from email");
        }
        resetPasswordPage.navigateTo(resetUrl);

        resetPasswordPage.fillResetPasswordForm(newPassword, mismatchedConfirmPassword);

        resetPasswordPage.clickResetPasswordButton();

        Assert.assertEquals(
                resetPasswordPage.getGeneralErrorMessage(),
                Message.RESET_PASSWORD_COULD_NOT_RESET);
        Assert.assertEquals(
                resetPasswordPage.getConfirmPasswordErrorMessage(),
                Message.ERROR_CONFIRM_PASSWORD_FIELD_MESSAGE_NOT_DISPLAYED);
    }
}