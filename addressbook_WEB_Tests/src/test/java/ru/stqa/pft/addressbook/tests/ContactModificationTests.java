package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testUserModification() throws Exception{
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        if (! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoGroupPage();
            app.getGroupHelper().createGroup(new GroupData("test", null, null));
            app.getNavigationHelper().gotoHomePage();
            app.getContactHelper().createContact(new ContactData("Test", null, null,
                    "test@mail.com", "887878777"));
            app.getNavigationHelper().gotoHomePage();
        }
        //модификация последнего контакта
        app.getContactHelper().editContact(before.size() - 1 );
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Testik",
                "Modificov", "New st.", "notnull@null.com",  "887878777");
        app.getContactHelper().fillContactForm(contact,false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(),before.size());

        before.remove(before.size() - 1);
        before.add(contact);

        Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));

    }
}
