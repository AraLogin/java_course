package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RemoveContactFromGroup extends  TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if (app.db().contacts().size() == 0){
        app.goTo().GroupPage();
        if (app.db().groups().size() == 0){
            app.group().create(new GroupData().withName("test2").withFooter("test1")
                    .withHeader("test3"));
            app.goTo().homePage();
        }
        app.contact().create(new ContactData().withFirstname("Test").withLastname("Our")
                .withAddress("HOME").withEmail("test@mail.com").withHomePhone("111")
                .withMobilePhone("222").withWorkPhone("333"));
    }
}
    @Test
    public void testRemoveFromGroup() {
        Contacts contacts = app.db().contacts();
        ContactData selectContact = getSelectContact(contacts);
        GroupData selectGroup = selectContact.getGroups().iterator().next();
        int beforeDeletingGroup = selectContact.getGroups().size();
        app.contact().removeContactFromGroup(selectGroup, selectContact);
        contacts = app.db().contacts();
        ContactData findContact = getFindContact(contacts, selectContact.getId());
        int afterDeletingGroup = findContact.getGroups().size();
        assertThat(afterDeletingGroup, equalTo(beforeDeletingGroup - 1));
    }
    public ContactData getSelectContact(Contacts contact) {
        return contact.stream().filter((c) -> c.getGroups().size() > 0).findFirst().get();
    }
    private ContactData getFindContact(Contacts contact, int contactId) {
        return contact.stream().filter((c) -> c.getId() == contactId).findFirst().get();
    }

}
