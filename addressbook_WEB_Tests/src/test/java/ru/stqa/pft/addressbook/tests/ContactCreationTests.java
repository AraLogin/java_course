package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class ContactCreationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().GroupPage();
        if (app.contact().all().size() == 0) {
            app.group().create(new GroupData().withName("test2").withFooter("test1").withHeader("test3"));
        }
    }

    @Test (enabled = true)
    public void testUserCreationTests() throws Exception {
        app.goTo().homePage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("test2").withLastname("testov")
                .withAddress("SAINT-P").withEmail("null@null.com").withMobile("7777");

        app.contact().create(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(),before.size() + 1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(after,before);
    }
}

