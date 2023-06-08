package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testUserDeletionTests() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getUserHelper().isThereAContact()){
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
            app.getNavigationHelper().gotoHomePage();
            app.getUserHelper().createContact(new ContactData("Test", null,
                    null, "Testik", "test inc.", "887878777"));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getUserHelper().selectContact();
        app.getUserHelper().deleteContact();
        app.wd.switchTo().alert().accept();
        app.getNavigationHelper().gotoHomePage();
    }
}
