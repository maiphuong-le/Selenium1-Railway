package testCases;

import Common.DriverManager;
import Config.ExtentManager;
import DataTypes.Station;
import Model.Account;
import PageObjects.*;
import dataTest.DataTests;
import messages.Message;
import config.BaseTest;
import Model.Ticket;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;

public class BookTicketTest extends BaseTest {

    HomePage homePage;
    RegisterPage registerPage;
    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    TimeTablePage timetablePage;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage();
        registerPage = new RegisterPage();
        loginPage = new LoginPage();
        bookTicketPage = new BookTicketPage();
        timetablePage = new TimeTablePage();
    }

    @Test(dataProvider = "bookOneTicketData", dataProviderClass = DataTests.class,
            description = "TC14 - User can book 1 ticket at a time")
    public void TC14(Account account, Ticket ticket) {
        extentTest = ExtentManager.createTest("TC14", "User can book 1 ticket at a time");

        homePage.open();
        homePage.clickRegisterTab();
        registerPage.register(account.getEmail(), account.getPassword(), account.getConfirmPassword(), account.getPid());

        homePage.clickLoginTab();
        loginPage.login(account.getEmail(), account.getPassword());

        homePage.clickBookTicketTab();
        bookTicketPage.bookTicket(ticket);

        String actualSuccessMessage = DriverManager.getDriver()
                .findElement(By.xpath("//h1[text()='Ticket booked successfully!']")).getText();
        Assert.assertEquals(actualSuccessMessage, Message.TICKET_BOOKED_SUCCESSFULLY,
                "Success message 'Ticket booked successfully!' is not displayed correctly.");

        Assert.assertTrue(bookTicketPage.isTicketInformationDisplayed(ticket),
                "Booked ticket information (Depart Date, Depart Station, Arrive Station, Seat Type, Amount) is not displayed correctly.");
    }

    @Test(dataProvider = "bookTicketFromHueToSaiGon", dataProviderClass = DataTests.class,
            description = "TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page")
    public void TC15(Account account, Station departFrom, Station arriveAt) {
        extentTest = ExtentManager.createTest("TC15", "User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");

        homePage.open();
        homePage.clickRegisterTab();
        registerPage.register(account.getEmail(), account.getPassword(), account.getConfirmPassword(), account.getPid());

        homePage.clickLoginTab();
        loginPage.login(account.getEmail(), account.getPassword());

        homePage.clickTimeTablePage();

        timetablePage.clickBookTicketLink(departFrom, arriveAt);

        String actualHeader = DriverManager.getDriver().findElement(By.tagName("h1")).getText();
        Assert.assertEquals(actualHeader, "Book ticket", "Header 'Book ticket' is not displayed correctly.");

        Assert.assertEquals(bookTicketPage.getDepartFrom(), departFrom.getStationName(), "Depart station does not match the selected one.");
        Assert.assertEquals(bookTicketPage.getArriveAt(), arriveAt.getStationName(), "Arrive station does not match the selected one.");
    }



}
