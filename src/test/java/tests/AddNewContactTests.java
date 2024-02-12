package tests;

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class AddNewContactTests extends TestBase {

    @BeforeClass(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("mara@gmail.com").withPassword("Mmar123456$"));
        }

    }

    @Test(dataProvider = "contactSuccess", dataProviderClass = DataProviderContact.class)
    public void addContactSuccessAllFields(Contact contact) {
       int i = (int) (System.currentTimeMillis() / 1000 % 3600);
//        Contact contact = Contact.builder()
//                .name("Tony"+i)
//                .lastName("Stark")
//                .address("NY")
//                .phone("3565946" + i)
//                .email("stark" + i + "@gmail.com")
//                .description("all fields")
//                .build();
        logger.info("Tests run with data: --->"+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().getScreen("src/test/screenshots/screen-"+i+".png");
        //app.getHelperContact().pause(2500);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));


    }
//firefox
    @Test (groups = {"smoke", "regress", "retest"})
    public void addContactSuccessRequiredFields() {
        int i = (int) (System.currentTimeMillis() / 1000 % 3600);
        Contact contact = Contact.builder()
                .name("TonyReq"+i)
                .lastName("Stark")
                .address("NY")
                .phone("35659446" + i)
                .email("stark" + i + "@gmail.com")
                .build();
        logger.info("Tests run with data: --->"+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(2500);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

    }

    @Test
    public void addNewContactWrongName(){
        Contact contact = Contact.builder()
                .name("")
                .lastName("Stark")
                .address("NY")
                .phone("35659445613")
                .email("stark@gmail.com")
                .description("empty name")
                .build();
        logger.info("Tests run with data: --->"+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(2500);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongAddress(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Stark")
                .address("")
                .phone("35659445613")
                .email("stark@gmail.com")
                .description("empty address")
                .build();
        logger.info("Tests run with data: --->"+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(2500);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());


    }

    @Test
    public void addNewContactWrongLastName(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("")
                .address("NY")
                .phone("35659445613")
                .email("stark@gmail.com")
                .description("empty lastname")
                .build();
        logger.info("Tests run with data: --->"+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(2500);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());


    }

    @Test(dataProvider = "contactWrongPhone", dataProviderClass = DataProviderContact.class)
    public void addNewContactWrongPhone(Contact contact){
//        Contact contact = Contact.builder()
//                .name("Tony")
//                .lastName("Stark")
//                .address("NY")
//                .phone("")
//                .email("stark@gmail.com")
//                .description("empty phone")
//                .build();
        logger.info("Tests run with data: --->"+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(2500);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));


    }

    @Test
    public void addNewContactWrongEmail(){
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Stark")
                .address("NY")
                .phone("12345678123")
                .email("starkgmail.com")
                .description("wrong email")
                .build();
        logger.info("Tests run with data: --->"+contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(1500);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid"));



    }

}
