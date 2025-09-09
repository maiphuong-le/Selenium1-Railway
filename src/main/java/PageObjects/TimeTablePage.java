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
        scrollClickHandler.click((WebElement) By.xpath(xpath));
        return this;
    }
}
