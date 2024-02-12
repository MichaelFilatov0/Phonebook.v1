package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("mara@gmail.com").withPassword("Mmar123456$"));
        }
        //if list<3 ===>add 3 contacts
        app.getHelperContact().provideContacts();

    }

    @Test(groups = {"smoke"})
    public void removeOneContact(){
        Assert.assertEquals(app.getHelperContact().removeOneContact(),1);
        //Assert size list less by one
    }
    @Test
    public void removeAllContacts(){
        app.getHelperContact().removeAllContacts();
        Assert.assertEquals(app.getHelperContact().getMessage(), "No Contacts here!");
        //"No contacts Here

    }
}
