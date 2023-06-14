package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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


    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        //File photo = new File("src/test/resources/i.webp");
        BufferedReader reader = new BufferedReader( new FileReader(new File("src/test/resources/contacts.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test (dataProvider = "validContacts")
    public void testContactCreation(ContactData contact)  {
        app.goTo().homePage();
        Contacts before = app.contact().all();
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

