package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testUserCreationTests() throws Exception {
        int before = app.getContactHelper().getContactCount();
        if (!app.getContactHelper().isThereAGroup()) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().createContact(new ContactData("test","te","testovich",
                "tt","trt","556345"));
        app.getNavigationHelper().gotoHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after,before + 1);
    }
}

