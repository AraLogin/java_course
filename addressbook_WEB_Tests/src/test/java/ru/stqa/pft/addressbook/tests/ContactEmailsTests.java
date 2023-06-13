package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactEmailsTests extends TestBase{


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
                    .withAddress("HOME").withEmail("null@null.com").withEmail2("null@gmail.com")
                    .withEmail3("null@yandex.ru").withHomePhone("2222")
                    .withMobilePhone("887878777").withWorkPhone("44444"));
            app.goTo().homePage();
        }
    }

    @Test
    public void testContactEmails(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

    }


    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter((s) -> !s.equals(""))
                //.map(ContactEmailsTests ::cleaned)
                .collect(Collectors.joining("\n"));
    }

    //public static String cleaned(String email) {return phone.replaceAll("\\s","").replaceAll("[-()]","");}
}

