package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private String browser;
    public WebDriver wd;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private  NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private JavascriptExecutor js;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equals(Browser.CHROME.browserName())) {
            wd = new ChromeDriver();
        } else if (browser.equals(Browser.FIREFOX.browserName())){
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary(new FirefoxBinary(new File("/usr/local/bin/firefox")));
            wd = new FirefoxDriver(options);
        } else if (browser.equals(Browser.IE.browserName())){
            wd = new InternetExplorerDriver();
        }
        //Изменение времени ожидания для FF на 1сек., т.к он периодически не успевает прогрузить эелементы страницы
        if (browser.equals(Browser.FIREFOX.browserName())){
            wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        } else {
            wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        }
        //js = (JavascriptExecutor) wd;
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login("admin", "secret");
    }
    public void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }
    public void stop() {
        wd.quit();
    }
    public GroupHelper group() {
        return groupHelper;
    }
    public NavigationHelper goTo() {
        return navigationHelper;
    }
    public ContactHelper contact() {
        return contactHelper;
    }
}
