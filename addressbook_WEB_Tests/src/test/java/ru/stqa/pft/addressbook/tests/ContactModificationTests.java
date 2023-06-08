package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testUserModification() throws Exception{
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
            app.getNavigationHelper().gotoHomePage();
            app.getContactHelper().createContact(new ContactData("Test", null, null,
                    "Testik", "test inc.", "887878777"));
            app.getNavigationHelper().gotoHomePage();
        }
        //модификация последнего контакта
        app.getContactHelper().editContact(before -1);
        app.getContactHelper().fillContactForm(new ContactData("Editor", "Testo", "Testov",
                "Testik", "test inc.", "8855578777"),false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before - 1);
    }
}
