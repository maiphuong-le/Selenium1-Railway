package Testcases;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Constant.Constant;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AccessControlTest {

    @BeforeMethod
    public void beforeMethod() {
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - Login page displays when un-logged user accesses protected page");

        HomePage homePage = new HomePage();
        homePage.open();

        Constant.WEBDRIVER.findElement(By.xpath("//a[@href='/Page/BookTicketPage.cshtml']")).click();

        String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("Login"),
                "User is not redirected to Login page when accessing Book Ticket without logging in");
    }

    @Test
    public void TC06() {
        System.out.println("TC06 - Additional pages display once user logged in");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        homePage = loginPage.login(Constant.USERNAME, Constant.PASSWORD);

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
