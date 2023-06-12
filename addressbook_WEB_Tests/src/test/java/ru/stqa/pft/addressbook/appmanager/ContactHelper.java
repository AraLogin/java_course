package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase{

    ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData userData, boolean creation) {
        type(By.name("firstname"),userData.getFirstname());
        type(By.name("lastname"),userData.getLastname());
        type(By.name("address"),userData.getAddress());
        type(By.name("email"), userData.getEmail());
        type(By.name("mobile"),userData.getMobile());

        //Проверка на наличие поля new_group, если creation=true,значит при создании оно должно быть. Если эдит, то нет
        if (creation ){
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectById(int id) {
        wd.findElement((By.cssSelector("input[value = '" + id + "']"))).click();
    }

    public void editContactById(int id){
        wd.findElement((By.cssSelector("a[href='edit.php?id=" + id + "']"))).click();
    }
    public void delete() {click(By.xpath("//input[@value='Delete']"));}

    public void updateContact() {click(By.xpath("//div[@id='content']/form/input[22]"));}
    public void returnToHomePage() {click(By.linkText("home"));}


    public void create(ContactData user) {

        initContactCreation();
        fillContactForm(user,true);
        submitContactCreation();
        returnToHomePage();
        contactCache = null;
    }
    public void modify(ContactData contact) {
        editContactById(contact.getId());
        fillContactForm(contact,false);
        updateContact();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        delete();
        wd.switchTo().alert().accept();
        contactCache = null;
        returnToHomePage();
    }


    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//td/input"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements((By.name("selected[]"))).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@name='entry']"));
        for (WebElement element : elements) {
            String lastname = element.findElement(By.xpath("td[2]")).getText();
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contactCache;
    }
}

