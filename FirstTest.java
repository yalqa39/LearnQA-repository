import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.ws.WebEndpoint;
import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUP() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/alexeyyalanskiy/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

 //   @Test
 //   public void FirstTest()
 //   {
 //       waitForElementByXpathAndClick(
 //               "//*[contains(@text,'Search Wikipedia')]",
 //               "Cannot find 'Search Wikipedia' input",
 //               5l
 //       );

  //      waitForElementByXpathAndSendKeys(
  //              "//*[contains(@text,'Search…')]",
  //              "Java",
  //              "Cannot find search input",
  //              5L
   //     );

   //     waitForElementPresentByXpath(
   //             "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']",
   //             "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
   //             15l
   //     );
   // }

    @Test
    public void testCancelSearch()
    {
        waitForElementPresentById(
       "org.wikipedia:id/search_container",
                "Cannot find 'Search Wikipedia' input",
                5L
        );
        waitForElementPresentById(
                "org.wikipedia:id/search_close_btn",
                "Cannot find X to cancel search",
                5L
        );

        waitForElementNotPresent(
                "org.wikipedia:id/search_close_btn",
                "X is still present on the page",
                5L
        );
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message, Long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresentByXpath(String xpath, String error_message)
    {
       return waitForElementPresentByXpath(xpath,error_message,5l);
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String error_message, Long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresentByXpath(xpath,error_message,5l);
        element.click();
        return element;
    }
    
    private WebElement waitForElementByXpathAndSendKeys(String xpath, String value, String error_message, Long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresentByXpath(xpath,error_message,5l);
        element.sendKeys(value);
        return element;
    }
    private WebElement waitForElementPresentById(String id, String error_message, Long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementByIdAndClick(String id, String error_message, Long timeoutInSeconds)
    {
        WebElement element =  waitForElementPresentById(id,error_message,5l);
        element.click();
        return element;
    }

    private boolean waitForElementNotPresent(String id, String error_message, Long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }
}


