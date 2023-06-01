package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase {

    @Test
    public void testUserCreationTests() throws Exception {
        app.getUserHelper().initUserCreation();
        app.getUserHelper().fillUserForm(new UserData("Test", null, null, "Testik", "test inc.", "887878777", "Test1"),true);
        app.getUserHelper().addUser();
        app.logout();
    }
}
