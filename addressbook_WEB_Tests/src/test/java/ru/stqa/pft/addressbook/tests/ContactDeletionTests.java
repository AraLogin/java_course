package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.contact().list().size() == 0){
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("test2").withFooter("test1")
                    .withHeader("test3"));
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Test").withLastname("Our")
                    .withAddress("HOME").withEmail("test@mail.com").withMobile("887878777"));
            app.goTo().homePage();
        }
    }
    @Test (enabled = true)
    public void testUserDeletionTests() throws Exception {
        app.goTo().homePage();
        List<ContactData> before = app.contact().list();
        app.contact().delete(before);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(),before.size() - 1);
        before.remove(before.size() - 1);
        Assert.assertEquals(before,after);

    }


}
