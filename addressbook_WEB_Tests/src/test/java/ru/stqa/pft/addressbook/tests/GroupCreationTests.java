package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test  //фикстура - с английского зазим,т.е тестовый метод зажимается между инициализацией и завершением фикстуры
    public void testGroupCreationTests() throws Exception {
        app.goTo().GroupPage();
        Groups before =app.group().all();
        GroupData group = new GroupData().withName("test2").withFooter("test1");
        app.group().create(group);
        app.goTo().GroupPage();
        assertThat(app.group().Count(),equalTo(before.size() + 1));
        Groups after =app.group().all();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream()
                .mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test
    public void testBadGroupCreationTests() throws Exception {
        app.goTo().GroupPage();
        Groups before =app.group().all();
        GroupData group = new GroupData().withName("test2'").withFooter("test1");
        app.group().create(group);
        app.goTo().GroupPage();
        assertThat(app.group().Count(),equalTo(before.size() ));
        Groups after =app.group().all();
        assertThat(after, equalTo(before));

    }
}
