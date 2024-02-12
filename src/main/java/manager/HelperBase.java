package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HelperBase {

    WebDriver wd;

    Logger logger = LoggerFactory.getLogger(HelperBase.class);

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }


    public void click(By locator) {
        WebElement element = wd.findElement(locator);
        element.click();

    }

    public boolean isElementPresent(By locator) {
        List<WebElement> list = wd.findElements(locator);
        return list.size() > 0;
    }


    public void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAlertPresent(String message) {
        Alert alert = new WebDriverWait(wd, 10)
                .until(ExpectedConditions.alertIsPresent());
        if (alert != null && alert.getText().contains(message)) {
            System.out.println(alert.getText());

            //click ok
            alert.accept();
            //click cancel ---> alert.dismiss();
            //type into alert ---> alert.sendKeys();
            return true;
        }
        return false;

    }


    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        element.clear();
        clearNew(element);
        if (text != null) {
            //System.out.println("hello");
            element.sendKeys(text);
        }


    }


    public void clearNew(WebElement element) {
        element.sendKeys(" ");
        element.sendKeys(Keys.BACK_SPACE);

    }

    public void getScreen(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp,new File(link));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isNoContactsHereDisplayed() {
        WebDriverWait wait = new WebDriverWait(wd, 5);
        boolean res = wait.until(ExpectedConditions.textToBePresentInElement
                (wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")), "No Contacts here!"));
        return res;
    }

    public String getMessage() {
        WebDriverWait wait = new WebDriverWait(wd, 5);
        wait.until(ExpectedConditions.visibilityOf(wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1"))));
        return wd.findElement(By.cssSelector(".contact-page_message__2qafk>h1")).getText();
    }
}
