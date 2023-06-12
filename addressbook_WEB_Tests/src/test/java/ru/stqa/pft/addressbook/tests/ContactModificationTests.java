package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.goTo().GroupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("test"));
                app.goTo().homePage();
            }
            app.contact().create(new ContactData().withFirstname("test2").withLastname("testov")
                    .withAddress("SAINT-P").withEmail("null@null.com").withMobile("7777"));
            app.goTo().homePage();

        }
    }
    @Test //(enabled = false)
    public void testUserModification() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname("Testik").withLastname("Modificov").withAddress("New st.")
                .withEmail("notnull@null.com").withMobile("887878777");

        //модификация последнего контакта
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
