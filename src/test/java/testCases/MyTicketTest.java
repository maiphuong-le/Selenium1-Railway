package testCases;

import Common.DriverManager;
import Config.ExtentManager;
import Model.Account;
import PageObjects.*;
import dataTest.DataTests;
import Model.Ticket;
import config.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class MyTicketTest extends BaseTest {

    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    MyTicketPage myTicketPage;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        bookTicketPage = new BookTicketPage();
        myTicketPage = new MyTicketPage();
    }

    @Test(dataProvider = "ticket_data_cancel", dataProviderClass = DataTests.class,
            description = "TC16 - User can cancel a ticket")
    public void TC16(Account account, Ticket ticket) {
        extentTest = ExtentManager.createTest("TC16", "User can cancel a ticket");

        homePage.open();
        homePage.clickRegisterTab();
        registerPage.register(account.getEmail(), account.getPassword(), account.getConfirmPassword(), account.getPid());

        homePage.clickLoginTab();
        loginPage.login(account.getEmail(), account.getPassword());

        homePage.clickBookTicketTab();
        String ticketId = bookTicketPage.BookAndGetId(ticket);

        homePage.clickMyTicketTab();
        myTicketPage.cancelTicketById(ticketId);

        Assert.assertFalse(myTicketPage.isTicketIdExists(ticketId), "Ticket is not cancelled successfully.");
    }
}
