package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test  //фикстура - с английского зазим,т.е тестовый метод зажимается между инициализацией и завершением фикстуры
    public void testGroupCreationTests() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("Test1", null, null));
        List<GroupData> before =app.getGroupHelper().getGroupList();
        app.getNavigationHelper().gotoGroupPage();
        List<GroupData> after =app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() +1);
    }

}
