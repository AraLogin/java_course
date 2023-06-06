package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserDeletionTests extends TestBase{

    @Test
    public void testUserDeletionTests() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getUserHelper().isThereAUser()){
            app.getUserHelper().createUser(new UserData("Test", null, null,
                    "Testik", "test inc.", "887878777", "Test1"));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getUserHelper().selectUser();
        app.getUserHelper().deleteUser();
        app.wd.switchTo().alert().accept();
        app.getNavigationHelper().gotoHomePage();
    }
}
