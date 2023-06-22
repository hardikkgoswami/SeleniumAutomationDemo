package Test;


import Utils.BasePage;
import Utils.Common;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;


public class FlightSearchTest extends BasePage {
    public WebDriver driver;
    Common common = new Common(driver);

     /**
      *  Login Module
      * @throws InterruptedException
      * @throws IOException
      */
    @Test
    public void searchFlightTest() throws InterruptedException, IOException, AWTException {

        // Open Expedia.com
        flightSearchPage.GotoURL();
        flightSearchPage.clickflightsTab();
        flightSearchPage.enterFlightDetails();
/*
        // Select departure date
        flightSearchPage.selectDepartureDate();

        // Select return date
        flightSearchPage.selectReturnDate();

        // Click search button
        flightSearchPage.clickSearchButton();

        // Sort by price (asc)
        flightSearchPage.sortByPriceLowToHigh();

        // Get details of the costliest flight (onward journey)
        String onwardJourneyCost = flightSearchPage.getOnwardJourneyCost();
        String flightName = flightSearchPage.getFlightName();
        String travelTime = flightSearchPage.getTravelTime();

        // Assert the results
        Assert.assertNotNull(onwardJourneyCost);
        Assert.assertNotNull(flightName);
        Assert.assertNotNull(travelTime);

        System.out.println("Onward Journey:");
        System.out.println("Costliest Flight -");
        System.out.println("Flight Name: " + flightName);
        System.out.println("Ticket Cost: " + onwardJourneyCost);
        System.out.println("Travel Time: " + travelTime); */
    }

  


}
