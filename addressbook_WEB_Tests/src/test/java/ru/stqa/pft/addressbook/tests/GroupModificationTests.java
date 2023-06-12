package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().GroupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("Test1"));
        }
    }
    @Test
    public void testGroupModification(){
        Groups before =app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId((modifiedGroup.getId())).withName("Test1")
                .withHeader("Test2").withFooter("Test3");
        app.group().modify(group);
        MatcherAssert.assertThat(app.group().Count(),equalTo(before.size()));
        Groups after =app.group().all();
        assertThat(after, CoreMatchers.equalTo(before.without(modifiedGroup)
                .withAdded(group)));
    }
}
