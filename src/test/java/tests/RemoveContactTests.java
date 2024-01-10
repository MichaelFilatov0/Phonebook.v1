package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("a@2.com").withPassword("Mm@2756808"));
        }
        app.getHelperContact().provideContacts();

    }

    @Test
    public void removeOneContact(){
        app.getHelperUser().pause(2000);
       Assert.assertTrue(app.getHelperUser().isElementPresent(By.cssSelector(".contact-page_leftdiv__yhyke")));
       app.getHelperUser().click(By.cssSelector(".contact-page_leftdiv__yhyke"));
       app.getHelperUser().click(By.xpath("//button[normalize-space()='Remove']"));
    }
    @Test
    public void removeAllContacts(){
        app.getHelperUser().pause(2000);
       do {
           app.getHelperUser().click(By.xpath("//div[@class='contact-page_leftdiv__yhyke']//div//div[1]"));
           app.getHelperUser().click(By.xpath("//button[normalize-space()='Remove']"));
       }while ((app.getHelperUser().isElementPresent(By.xpath("//h1[normalize-space()='No Contacts here!']"))!=true));

    }
}
