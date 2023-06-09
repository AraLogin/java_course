package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testUserDeletionTests() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
            app.getNavigationHelper().gotoHomePage();
            app.getContactHelper().createContact(new ContactData("Test", null, null,
                    "test@mail.com", "887878777"));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().selectContact(before -1);
        app.getContactHelper().deleteContact();
        app.wd.switchTo().alert().accept();
        app.getNavigationHelper().gotoHomePage();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before - 1);
    }
}
