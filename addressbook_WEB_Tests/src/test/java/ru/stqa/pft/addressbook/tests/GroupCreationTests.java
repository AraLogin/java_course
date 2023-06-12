package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test  //фикстура - с английского зазим,т.е тестовый метод зажимается между инициализацией и завершением фикстуры
    public void testGroupCreationTests() throws Exception {
        app.goTo().GroupPage();
        Set<GroupData> before =app.group().all();
        GroupData group = new GroupData().withName("test2").withFooter("test1");
        app.group().create(group);
        app.goTo().GroupPage();
        Set<GroupData> after =app.group().all();
        Assert.assertEquals(after.size(), before.size() +1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals (before,after);
    }

}
