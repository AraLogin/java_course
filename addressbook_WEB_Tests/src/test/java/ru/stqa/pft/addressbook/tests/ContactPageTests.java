package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPageTests extends TestBase {

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
                    .withAddress("HOME").withEmail("test@mail.com").withHomePhone("2222")
                    .withMobilePhone("887878777").withWorkPhone("44444"));
            app.goTo().homePage();
        }
    }
    @Test
    public void testsContactPage(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
        
    }
    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactPageTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
    public static String cleaned(String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }
}
