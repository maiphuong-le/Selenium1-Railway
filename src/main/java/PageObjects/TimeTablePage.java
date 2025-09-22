package PageObjects;

import Common.DriverManager;
import Common.ScrollClickHandler;
import DataTypes.Station;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TimeTablePage {

    private ScrollClickHandler scrollClickHandler;

    public TimeTablePage() {
        scrollClickHandler = new ScrollClickHandler(DriverManager.getDriver());
    }

    public TimeTablePage clickBookTicketLink(Station departFrom, Station arriveAt) {
        String xpath = String.format("//tr[td[2]='%s' and td[3]='%s']//a[text()='book ticket']",
                departFrom.getStationName(), arriveAt.getStationName());
        try {
            WebElement bookTicketLink = DriverManager.getDriver().findElement(By.xpath(xpath));
            scrollClickHandler.click(bookTicketLink);
        } catch (Exception e) {
            // Try alternative selector
            String alternativeXpath = String.format("//tr[contains(., '%s') and contains(., '%s')]//a[contains(@href, 'BookTicket') or text()='book ticket']",
                    departFrom.getStationName(), arriveAt.getStationName());
            WebElement bookTicketLink = DriverManager.getDriver().findElement(By.xpath(alternativeXpath));
            scrollClickHandler.click(bookTicketLink);
        }
        return this;
    }
}
