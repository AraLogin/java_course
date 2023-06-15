package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {
    //выбор браузера/ задали статик для открытия всех тестов в одном браузере
    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    @BeforeSuite
            (alwaysRun = true) //инициализация фикустуры
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true) //завершение фикстуры
    public void tearDown() throws Exception {
        app.logout();
        app.stop();
    }

}
