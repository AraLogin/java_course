package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

    @Test  //фикстура - с английского зазим,т.е тестовый метод зажимается между инициализацией и завершением фикстуры
    public void testGroupCreationTests() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("Test1", null, null));
        app.getNavigationHelper().gotoGroupPage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before +1);
    }

}
