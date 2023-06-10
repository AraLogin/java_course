package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testUserCreationTests() throws Exception {

        if (!app.getContactHelper().isThereAGroup()) {
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
        }
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("test","testov","SAINT-P",
                "null@null.com","7777");
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size() + 1);

        int max = 0;
        for (ContactData c : after){
            if (c.getId() > max){
                max = c.getId();
            }
        }
        contact.setId(max);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    }
}

