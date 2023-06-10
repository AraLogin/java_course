package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.contact().list().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData("test", null, null));
            app.goTo().homePage();
            app.contact().create(new ContactData("test2", "testov", "SAINT-P",
                    "null@null.com", "7777"));
            app.goTo().homePage();
        }
    }
    @Test (enabled = true)
    public void testUserModification() throws Exception{
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Testik",
                "Modificov", "New st.", "notnull@null.com",  "887878777");
        int index = before.size() -1;
        //модификация последнего контакта
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1,c2) -> Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);

    }


}
