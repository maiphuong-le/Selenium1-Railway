package testCases;

import Config.ExtentManager;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import com.aventstack.extentreports.ExtentTest;
import config.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import messages.Message;
import Constant.Constant;
import org.testng.asserts.SoftAssert;
import Common.DriverManager;

public class AccessControlTest extends BaseTest {

    ExtentTest extentTest;
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
    public void TC04() {
        extentTest = ExtentManager.createTest("TC04", "Login page displays when unlogged user accesses protected page");
        homePage.open();
        homePage.clickBookTicketTab();
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("Login"),
                "User is not redirected to Login page when accessing Book Ticket without logging in");

        softAssert.assertAll();
    }

    @Test(description = "Additional pages display once user logged in")
    public void TC06() {
        extentTest = ExtentManager.createTest("TC06", "Additional pages display once user logged in");
        homePage.open();

        loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), Message.MY_TICKET_TAB_NOT_DISPLAYED);
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), Message.CHANGE_PASSWORD_TAB_NOT_DISPLAYED);
        Assert.assertTrue(homePage.isLogoutTabDisplayed(), Message.LOGOUT_TAB_NOT_DISPLAYED);

        homePage.clickMyTicketTab();
        String currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("ManageTicket"), Message.NOT_NAVIGATED_TO_MY_TICKET);

        homePage.gotoChangePasswordPage();
        homePage.waitForUrlContains("ChangePassword");

        currentUrl = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("ChangePassword"), Message.NOT_NAVIGATED_TO_CHANGE_PASSWORD);

        softAssert.assertAll();
    }
}
