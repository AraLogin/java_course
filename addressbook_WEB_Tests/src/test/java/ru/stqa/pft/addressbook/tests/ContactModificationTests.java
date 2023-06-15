package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0) {
            app.goTo().GroupPage();
            if (app.db().groups().size() == 0) {
                app.group().create(new GroupData().withName("test"));
                app.goTo().homePage();
            }
            app.contact().create(new ContactData().withFirstname("test2").withLastname("testov")
                    .withAddress("SAINT-P").withEmail("null@null.com").withHomePhone("111")
                    .withMobilePhone("222").withWorkPhone("333"));
            app.goTo().homePage();
        }
    }
    @Test
    public void testUserModification() throws Exception {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstname("Testiko").withLastname("Modificov").withAddress("New st. 1")
                .withEmail("notnull@null.com").withEmail2("notnull@gmail.com")
                .withEmail3("notnull@mail.com").withHomePhone("444").withMobilePhone("5556")
                .withWorkPhone("6667").withHomePhone("3434");

        //модификация последнего контакта
        app.contact().modify(contact);
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
