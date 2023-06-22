package Page;

import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.FileNotFoundException;
import java.util.List;

public class AmazonSearchPage extends BasePage {

	WebDriver driver;
	private static final String AMAZON_URL = "https://www.amazon.com/";
	private By searchBox = By.id("twotabsearchtextbox");
	private By searchResults = By.xpath("//div[@class=\"s-main-slot s-result-list s-search-results sg-row\"]/div[@data-component-type=\"s-search-result\"]");
	public AmazonSearchPage(WebDriver d) throws FileNotFoundException {
		driver = d;
		PageFactory.initElements(this.driver, this);

	}

	public void GotoURL() {
		driver.get(AMAZON_URL);
		String expectedTitle = "Amazon.com";
		String actualTitle = driver.getTitle();
		//Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("URL Opened");

	}
	public void printTopThreeResults() {
		List<WebElement> results = driver.findElements(searchResults);

		for (int i = 0; i < 3; i++) {
			System.out.println("==================");
			WebElement result = results.get(i);
			WebElement titleElement = result.findElement(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']"));
			//WebElement colorElement = result.findElement(By.xpath(".//span[contains(@class, 'a-color-secondary')]"));
			WebElement priceElement = result.findElement(By.xpath(".//span[@class=\"a-price-whole\"]"));

			String title = titleElement.getText();
			//String color = colorElement.getText();
			String price = priceElement.getText();

			System.out.println("Result " + (i + 1) + ":");
			System.out.println("Title: " + title);
			//System.out.println("Color: " + color);

			System.out.println("Price: " + price);
		}
	}
	public void enterSearchQuery(String phoneModel) {
		WebElement searchBoxElement = driver.findElement(searchBox);
		searchBoxElement.sendKeys(phoneModel);
		System.out.println("Searched iPhone 13");
		searchBoxElement.submit();
		//common.log("Click Submit");
		System.out.println("Click Submit");
	}

}


