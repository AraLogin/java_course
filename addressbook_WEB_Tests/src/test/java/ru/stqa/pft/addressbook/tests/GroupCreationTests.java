package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test  //фикстура - с английского зазим,т.е тестовый метод зажимается между инициализацией и завершением фикстуры
    public void testGroupCreationTests() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> before =app.getGroupHelper().getGroupList();
        GroupData group = new GroupData("Test1", null, null);
        app.getGroupHelper().createGroup(group);
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> after =app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() +1);

        before.add(group);
        //нахождение максимального идентификатора(новой группы)
        int max = 0;
        //постепенноо сравниваем уже найденный макс с уже найденным эл-ом и если id > то увеличиваем
        for (GroupData g : after) {
            if (g.getId() > max) {
                max = g.getId();
            }
        }
        //меняем значение на новый найденный максимум
        group.setId(max);
        before.add(group);
        Assert.assertEquals (new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
