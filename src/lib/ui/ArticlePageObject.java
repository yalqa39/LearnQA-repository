package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
   private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON = "//android.widget.ImageView[@content_desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content_desc='Navigate up']";

   public ArticlePageObject(AppiumDriver driver)
   {
       super(driver);
   }

   public WebElement waitForTitleElement()
   {
       return this.waitForElementPresent(By.id(TITLE), "Cannot find article on page!", 15L);
   }

   public String getArticleTitle()
   {
       WebElement title_element = waitForTitleElement();
       return title_element.getAttribute("text");
   }

   public void swipeToFooter()
   {
       this.swipeUpToFindElement(
               By.xpath(FOOTER_ELEMENT),
               "Cannot find the end of article",
               20
       );
   }

   public void addArticleToMyList(String name_of_folder)
   {
        this.waitForElementAndClick(
               By.xpath(OPTIONS_BUTTON),
               "Cannot find button to open article options",
               5L
       );

       this.waitForElementAndClick(
               By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
               "Cannot find option to add article to reading list",
               5L
       );

       this.waitForElementAndClick(
               By.id(ADD_TO_MY_LIST_OVERLAY),
               "Cannot find 'Got it' tip overlay",
               5L
       );

       this.waitForElementAndClear(
               By.id(MY_LIST_NAME_INPUT),
               "Cannot find input to set name of articles folder",
               5L
       );

       this.waitForElementAndSendKeys(
               By.id(MY_LIST_NAME_INPUT),
               name_of_folder,
               "Cannot put text into articles folder input",
               5L
       );

       this.waitForElementAndClick(
               By.xpath(MY_LIST_OK_BUTTON),
               "Cannot press OK button",
               5L
       );
   }

   public void closeArticle()
   {
       this.waitForElementAndClick(
             By.xpath(CLOSE_ARTICLE_BUTTON),
             "Cannot close article, cannot find X link",
             5L
             );
   }
}
