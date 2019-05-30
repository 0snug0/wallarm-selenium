import java.net.URL;
import java.lang.Exception;
import java.util.concurrent.ThreadLocalRandom;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


/*****************************************************************************
 * Author:      Onur Baskirt
 * Description: This is the first Selenium TestNG test.
 *              It opens swtestacademy homepage and prints and checks its title.
*******************************************************************************/

public class FirstTest {

    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    public WebDriver driver;

    //Declare a test URL variable
    public String testURL = "https://google-gruyere.appspot.com/472451726985261055793521833337334384156";

    //-----------------------------------Test Setup-----------------------------------
    @BeforeTest
    public void setupTest (){
        //Create a new ChromeDriver
        // standard web driver
        //System.setProperty("webdriver.firefox", "C:/geckodriver.exe");
        //driver = new FirefoxDriver();

        //remote via selenium grid

        DesiredCapabilities capability = DesiredCapabilities.firefox();
        capability.setBrowserName("firefox");
        String proxy_server = "127.0.0.1:8091";
        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
        proxy.setHttpProxy(proxy_server).setFtpProxy(proxy_server).setSslProxy(proxy_server);
        capability.setCapability(CapabilityType.PROXY, proxy);



        try{
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub/"), capability);
        }
        catch( Exception ex) {ex.printStackTrace();}


        driver.get(testURL);


        //Set cookie
        Cookie ck = new Cookie("GRUYERE_ID", "472451726985261055793521833337334384156" );
        driver.manage().addCookie(ck);
        Cookie ck2 = new Cookie("GRUYERE", "110845406|test||author" );
        driver.manage().addCookie(ck2);
        driver.get(testURL);
    }

    //-----------------------------------Tests-----------------------------------
    @Test
    public void firstTest () {

        int randomNum = ThreadLocalRandom.current().nextInt(1, 100 + 1);


        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


    //-----------------------------------Test TearDown-----------------------------------
    @AfterTest
    public void teardownTest (){
        //Close browser and end the session
        driver.quit();
    }
}
