package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testUserCreationTests() throws Exception {
        if (!app.getUserHelper().isThereAGroup()) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
        }
        app.getNavigationHelper().gotoHomePage();
        app.getUserHelper().createContact(new ContactData("test","te","testovich",
                "tt","trt","556345"));
        app.getNavigationHelper().gotoHomePage();
    }
}

