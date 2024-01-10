package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;

    HelperUser helperUser;
    HelperContact helperContact;



    public void init() {
        wd = new ChromeDriver();
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.navigate().to("https://telranedu.web.app/");
        helperUser = new HelperUser(wd);
        helperContact = new HelperContact(wd);
    }

    public HelperUser getHelperUser() {
        return helperUser;
    }

    public HelperContact getHelperContact() {
        return helperContact;
    }

    public void stop() {
        wd.quit();

    }
}
