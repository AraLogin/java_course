package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(Browser.FIREFOX.browserName());//выбор браузера

    @BeforeMethod(alwaysRun = true) //инициализация фикустуры
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod(alwaysRun = true) //завершение фикстуры
    public void tearDown() throws Exception {
        app.stop();
    }

}
