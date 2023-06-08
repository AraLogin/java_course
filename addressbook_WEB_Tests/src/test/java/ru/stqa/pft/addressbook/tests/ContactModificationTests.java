package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testUserModification() throws Exception{
        app.getNavigationHelper().gotoHomePage();
        if (! app.getUserHelper().isThereAContact()){
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
            app.getNavigationHelper().gotoHomePage();
            app.getUserHelper().createContact(new ContactData("Test", null, null,
                    "Testik", "test inc.", "887878777"));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getUserHelper().editContact();
        app.getUserHelper().fillContactForm(new ContactData("Editor", "Testo", "Testov",
                "Testik", "test inc.", "8855578777"),false);
        app.getUserHelper().updateContact();
        app.getNavigationHelper().gotoHomePage();
    }
}
