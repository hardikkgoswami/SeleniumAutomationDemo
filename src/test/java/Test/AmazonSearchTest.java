package Test;
import Utils.BasePage;
import org.testng.annotations.Test;
import java.io.IOException;


public class AmazonSearchTest extends BasePage {

     /**
      *  AmazonSearch Module
      * @throws InterruptedException
      * @throws IOException
      */
    @Test
    public void AmazonSearch() throws InterruptedException, IOException {
        amazonSearchPage.GotoURL();
        amazonSearchPage.enterSearchQuery("iPhone 13");
        amazonSearchPage.printTopThreeResults();
    }
}
