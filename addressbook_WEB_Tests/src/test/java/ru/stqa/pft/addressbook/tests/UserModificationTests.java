package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {

    @Test
    public void testUserModification() throws Exception{
        app.getNavigationHelper().gotoHomePage();
        if (! app.getUserHelper().isThereAUser()){
            app.getUserHelper().createUser(new UserData("Test", null, null,
                    "Testik", "test inc.", "887878777", "test1"));
            app.getNavigationHelper().gotoHomePage();
        }
        app.getUserHelper().editUser();
        app.getUserHelper().fillUserForm(new UserData("Editor", "Testo", "Testov",
                "Testik", "test inc.", "8855578777","test"),false);
        app.getUserHelper().updateUser();
        app.getNavigationHelper().gotoHomePage();
    }
}
