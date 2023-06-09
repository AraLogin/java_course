package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test2", null, null));
    }
    //размер списка ДО
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getNavigationHelper().gotoGroupPage();
    //размер списка ПОСЛЕ
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //сравнение размера списков
    Assert.assertEquals(after.size(), before.size() - 1);
    //удаление старого списка
    before.remove(before.size() - 1);
    //сравнение элементов старого и нового списка
    Assert.assertEquals(before, after); }
  }