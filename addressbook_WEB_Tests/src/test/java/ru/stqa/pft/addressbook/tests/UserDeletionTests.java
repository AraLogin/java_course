package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class UserDeletionTests extends TestBase{

    @Test
    public void testUserDeletionTests() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        app.getUserHelper().selectUserId();
        app.getUserHelper().deleteUser();
        app.wd.switchTo().alert().accept();
        app.getNavigationHelper().gotoHomePage();
    }
}
