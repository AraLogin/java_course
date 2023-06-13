package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().GroupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test2").withFooter("test1").withHeader("test3"));
        }
    }

    @Test //(enabled = false)
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/i.webp");
        ContactData contact = new ContactData().withFirstname("test2").withLastname("testov")
                .withAddress("SAINT-P").withEmail("null@null.com").withEmail2("null@gmail.com")
                .withEmail3("null@yandex.ru").withHomePhone("11 11 11")
                .withMobilePhone("22-22-22").withWorkPhone("+7(333)888-99-99").withPhoto(photo);

        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(),equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream()
                .mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
    @Test (enabled = false)
    public void testBadContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("test2'").withLastname("testov")
                .withAddress("SAINT-P").withEmail("null@null.com").withEmail2("null@gmail.com")
                .withEmail3("null@yandex.ru").withHomePhone("111")
                .withMobilePhone("222").withWorkPhone("333");

        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(),equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}

