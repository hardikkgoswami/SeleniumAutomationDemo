package Utils;


import Page.AmazonSearchPage;
import Page.FlightSearchPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;


public class BasePage {
    String useragent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36";
    static Properties configProperties = null;

    protected WebDriver driver;
    private Common common = new Common(driver);
    public static String currentTest; // current running test
    protected static String test_data_folder_path = null;

    protected static String screenshot_folder_path = null;
    protected static Logger logger = Logger.getLogger("testing");
    public AmazonSearchPage amazonSearchPage;

    public FlightSearchPage flightSearchPage;

    // Set Firefox options
    FirefoxOptions options = new FirefoxOptions().addArguments("-private");
        //options.("-private");  // Enable private mode

    public Locators locators;

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) throws MalformedURLException, FileNotFoundException {
        currentTest = method.getName(); // get Name of current test.

        String SCREENSHOT_FOLDER_NAME = "screenshots";
        String TESTDATA_FOLDER_NAME = "test_data";

        test_data_folder_path = new File(TESTDATA_FOLDER_NAME)
                .getAbsolutePath();
        screenshot_folder_path = new File(SCREENSHOT_FOLDER_NAME)
                .getAbsolutePath();

        DesiredCapabilities capability = null;
        String driverPath = getPropertyValue("driverPath");
        String driverFirefoxPath = getPropertyValue("driverFirefoxPath");
        String browser = getPropertyValue("browser");
        String headless = getPropertyValue("headless");

// 			Mac Path
        System.setProperty("webdriver.chrome.driver",driverPath);

        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", driverPath);
            //WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
            new ChromeDriver(options);
//            options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
            if (headless.equals("true")) {
                options.addArguments("headless");
                //options.addArguments("--headless");
                options.addArguments("window-size=1536x768");
                //options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
            }

            driver = new ChromeDriver(options);
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", driverFirefoxPath);
            FirefoxOptions options = new FirefoxOptions();
            String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/114.0";
            options.addPreference("general.useragent.override",userAgent);

            //WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        if (!headless.equals("true")) {
            driver.manage().window().maximize();
            //driver.manage().window().fullscreen();
        }




//			driver.manage().window().maximize();

        //loginPage = new LoginPage(driver);
        //searchResultPage = new AmazonSearchResultsPage(driver);
        flightSearchPage = new FlightSearchPage(driver);
        amazonSearchPage = new AmazonSearchPage(driver);

    }

    protected Properties getConfigProperties() {
        if (configProperties == null) {
            configProperties = this.loadProperties(Paths.get("src/test/java/Config").toAbsolutePath().normalize().toString() + "//config.properties");

        }
        return configProperties;
    }

    protected String getPropertyValue(String value) {
        Properties properties = getConfigProperties();
        return properties.getProperty(value);
    }

    private Properties loadProperties(String filePath) {
        File file = new File(filePath);
        FileInputStream fileInput = null;

        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();

        try {
            properties.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }





    /**
     * After Method {TearDown}
     *
     * @param testResult
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult testResult) {
        try {

            String testName = testResult.getName();

            if (!testResult.isSuccess()) {

                /* Print test result to Jenkins Console */
                System.out.println();
                System.out.println("TEST FAILED - " + testName);
                System.out.println();
                // System.out.println("ERROR MESSAGE: "
                // + testResult.getThrowable());
                System.out.println("\n");
                Reporter.setCurrentTestResult(testResult);

                /* Make a screenshot for test that failed */
                // Create refernce of TakesScreenshot
                TakesScreenshot ts=(TakesScreenshot)driver;
                File source=ts.getScreenshotAs(OutputType.FILE);
                try{
                    FileHandler.copy(source, new File("./Screenshots/"+testResult.getName()+".png"));
                    System.out.println("Screenshot taken");
                }

            catch (Exception e)
            {
                System.out.println("Exception while taking screenshot "+e.getMessage());
            }

            String screenshotName = common.getCurrentTimeStampString()
                        + testName;
                Reporter.log("<br> <b>Please look to the screenshot - </b>");
            } else {
                System.out.println("TEST PASSED - " + testName + "\n"); // Print
                // test
                // result
                // to
                // Jenkins
                // Console
            }

            driver.manage().deleteAllCookies();
            driver.quit();

        } catch (Throwable throwable) {

        }
    }


}
