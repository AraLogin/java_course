package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.goTo().GroupPage();
            if (app.group().all().size() == 0){
                app.group().create(new GroupData().withName("test2").withFooter("test1")
                        .withHeader("test3"));
                app.goTo().homePage();
            }
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Our")
                    .withAddress("HOME").withEmail("test@mail.com").withMobile("887878777"));
            app.goTo().homePage();
        }
    }
    @Test (enabled = true)
    public void testUserDeletionTests() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        assertEquals(after.size(),before.size() - 1);
        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(deletedContact)));
    }
}
