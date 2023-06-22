package Page;

import Utils.BasePage;
import Utils.Common;
import Utils.Locators;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import com.sun.tools.javac.util.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public class FlightSearchPage extends BasePage {

	WebDriver driver;
	Common common = new Common(driver);
	Properties obj = new Properties();

	public By fromBoxLocator = By.xpath("//button[@aria-label='Leaving from']");
	public By fromInput = By.xpath("//div[@class='uitk-field has-no-visible-label has-placeholder']/input");
	public By toBoxLocator = By.xpath("//button[@aria-label=\"Going to\"]");
	public By toInput = By.xpath("//input[@id='location-field-leg1-destination']");
	public By departDateLocator = By.id("d1-btn");
	public By returnDateLocator = By.id("d2-btn");
	private By searchButtonLocator = By.xpath("//button[@data-testid='submit-button']");

	private By sortButtonLocator = By.xpath("//button[@data-testid='sortDropdownButton']");
	private By priceLowToHighLocator = By.xpath("//a[@data-value='price']");
	private By onwardJourneyCostLocator = By.xpath("//span[@data-testid='listing-price-dollars']");
	private By flightNameLocator = By.xpath("//span[@data-testid='airline-name']");
	private By travelTimeLocator = By.xpath("//span[@data-testid='duration']");

	public   By flightTab = By.xpath("//span[text()='Flights']");

   public void  expAutomation() {
	   // Select flight option
	   WebElement flightsTab = driver.findElement(By.id("uitk-tabs-button-container"));
	   flightsTab.click();

	   // Enter flight details
	   WebElement fromBox = driver.findElement(By.id("location-field-leg1-origin"));
	   fromBox.clear();
	   fromBox.sendKeys("LAX");
	   fromBox.sendKeys(Keys.RETURN);

	   WebElement toBox = driver.findElement(By.id("location-field-leg1-destination"));
	   toBox.clear();
	   toBox.sendKeys("JFK");
	   toBox.sendKeys(Keys.RETURN);

	   WebElement departDate = driver.findElement(By.id("d1-btn"));
	   departDate.click();

	   WebElement firstFriday = driver.findElement(By.xpath("//button[@data-day='5'][@data-stid='date-picker-paging'][1]"));
	   firstFriday.click();

	   WebElement returnDate = driver.findElement(By.id("d2-btn"));
	   returnDate.click();

	   WebElement thirdMonday = driver.findElement(By.xpath("//button[@data-day='15'][@data-stid='date-picker-paging'][3]"));
	   thirdMonday.click();

	   // Search for flights
	   WebElement searchButton = driver.findElement(By.xpath("//button[@data-testid='submit-button']"));
	   searchButton.click();

	   // Sorting by price (asc)
	   WebElement sortButton;
   }
	public void clickflightsTab() throws InterruptedException, AWTException {
	    // common.waitForElement(By.xpath("//a[@href='Flights']"));
		Thread.sleep(4000);
		System.out.println("2 Minutes over");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		//driver.findElement(By.xpath("//div[@class='uitk-menu-container animation-disabled uitk-menu-open uitk-menu-pos-right uitk-menu-container-autoposition uitk-menu-container-has-intersection-root-el']")).sendKeys(Keys.ESCAPE);
		driver.findElement(flightTab).click();
		common.pause(50);
		System.out.println("Flights tab clicked");
	}

	public void enterFlightDetails() throws InterruptedException, AWTException {
	   //common.pause(10);

	   //driver.findElement(By.xpath("//span[text()='One-way']")).click();

	   common.pause(5);
	   driver.findElement(By.xpath("//span[text()='Roundtrip']")).click();


	   common.pause(10);
		driver.findElement(fromBoxLocator).click();
		System.out.println("From Box Found");
		common.pause(10);


//		driver.findElement(fromInput).click();
		common.pause(10);

		WebElement el = driver.findElement(fromInput);
		String text = "LAX";
		for (char character : text.toCharArray()) {
			el.sendKeys(Character.toString(character));
			try {
				Thread.sleep(300); // Pause for 300 milliseconds (0.3 seconds)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("LAX Printed");
		common.pause(10);
		// Find multiple elements using a CSS selector
		List<WebElement> elements = driver.findElements(By.xpath("//ul[@class=\"uitk-action-list no-bullet\"]//li"));

		// Get the count of elements
		int elementCount = elements.size();
		System.out.println("Number of elements found: " + elementCount);


//		WebElement inputField =  driver.findElement(fromInput);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].value='Los Angeles, CA, United States of America (LAX-Los Angeles Intl.)';", inputField);

//		Actions a = new Actions(driver);
//		a.sendKeys("Los");
//		a.perform();


/*
Thread.sleep(10);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_I);
		common.pause(10);
*/



//		driver.findElement(By.xpath("//*[text()='One-way']")).click();
//
//		driver.findElement(By.xpath("//span[text()='Roundtrip']")).click();
//
//		common.pause(10);
//		driver.findElement(fromBoxLocator).click();
//		common.pause(10);
//
//		driver.findElement(fromInput).click();
//		common.pause(10);
//
//		a.sendKeys("Los");
//		a.perform();

		//	driver.findElement(fromInput).sendKeys("Los Angeles, CA, United States of America (LAX-Los Angeles Intl.");

		//driver.findElement(fromInput).sendKeys("la");
		//common.pause(8);
		//driver.findElement(fromInput).sendKeys("A");
		common.pause(8);

/*
		//common.pause(50);
	//	driver.findElement(fromBoxLocator).sendKeys(Keys.ENTER);
		common.pause(10);
		//driver.findElement(toBoxLocator).clear();
		driver.findElement(toBoxLocator).click();
		common.pause(5);
		//driver.findElement(toInput).sendKeys(to);
		common.pause(5);
		driver.findElement(toBoxLocator).sendKeys(Keys.ENTER);

 */
	}

	public void GotoURL() {
	   //driver.get("https://www.expedia.com/Flights-Search?flight-type=on&mode=search&trip=roundtrip&leg1=from:Los%20Angeles,%20CA,%20United%20States%20of%20America%20(LAX-Los%20Angeles%20Intl.),to:New%20York,%20NY,%20United%20States%20of%20America%20(JFK-John%20F.%20Kennedy%20Intl.),departure:7/7/2023TANYT&leg2=from:New%20York,%20NY,%20United%20States%20of%20America%20(JFK-John%20F.%20Kennedy%20Intl.),to:Los%20Angeles,%20CA,%20United%20States%20of%20America%20(LAX-Los%20Angeles%20Intl.),departure:7/17/2023TANYT&options=cabinclass:economy&fromDate=7/7/2023&toDate=7/17/2023&d1=2023-7-7&d2=2023-7-17&passengers=adults:1,infantinlap:N");
		driver.get("https://www.expedia.com/");
		//Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("URL Opened");
	}
	public void selectDepartureDate() {
	   common.pause(5);
		driver.findElement(departDateLocator).click();
		// Code to select the first Friday of July
	}

	public void selectReturnDate() {
		driver.findElement(returnDateLocator).click();
		// Code to select the third Monday of June
	}

	public void clickSearchButton() {
		driver.findElement(searchButtonLocator).click();
	}

	public FlightSearchPage(WebDriver driver) {
		this.driver = driver;
	}

	public void sortByPriceLowToHigh() {
		driver.findElement(sortButtonLocator).click();
		driver.findElement(priceLowToHighLocator).click();
	}

	public String getOnwardJourneyCost() {
		WebElement onwardJourneyCostElement = driver.findElement(onwardJourneyCostLocator);
		return onwardJourneyCostElement.getText();
	}

	public String getFlightName() {
		WebElement flightNameElement = driver.findElement(flightNameLocator);
		return flightNameElement.getText();
	}

	public String getTravelTime() {
		WebElement travelTimeElement = driver.findElement(travelTimeLocator);
		return travelTimeElement.getText();
	}
}

