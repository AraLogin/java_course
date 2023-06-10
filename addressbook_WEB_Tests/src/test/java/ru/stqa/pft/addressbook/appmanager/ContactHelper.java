package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void addContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData userData, boolean creation) {
        type(By.name("firstname"),userData.getFirstname());
        type(By.name("lastname"),userData.getLastname());
        type(By.name("address"),userData.getAddress());
        type(By.name("email"), userData.getEmail());
        type(By.name("mobile"),userData.getMobile());

        //Проверка на наличие поля new_group, если creation=true ,значит при создании оно должно быть. Если эдит, то нет
        if (creation ){
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void select(int index) { wd.findElements(By.xpath("//td/input")).get(index).click();}

    public void editContact(int index){wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();}
    public void delete() {click(By.xpath("//input[@value='Delete']"));}

    public void updateContact() {click(By.xpath("//div[@id='content']/form/input[22]"));}
    public void returnToHomePage() {click(By.linkText("home"));}


    public void create(ContactData user) {
        initContactCreation();
        fillContactForm(user,true);
        addContact();
    }
    public void modify(int index, ContactData contact) {
        editContact(index);
        fillContactForm(contact,false);
        updateContact();
        returnToHomePage();
    }
    public void delete(List<ContactData> before) {
        select(before.size() -1);
        delete();
        wd.switchTo().alert().accept();
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

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@name='entry']"));
        for (WebElement element : elements) {
            String lastname = element.findElement(By.xpath("td[2]")).getText();
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
            contacts.add(contact);
        }
        return contacts;
    }
}

