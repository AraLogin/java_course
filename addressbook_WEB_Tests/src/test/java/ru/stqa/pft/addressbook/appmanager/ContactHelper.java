package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;

public class ContactHelper extends HelperBase{

    ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void addUser() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillUserForm(UserData userData, boolean creation) {
        type(By.name("firstname"),userData.getFirstname());
        type(By.name("middlename"),userData.getMiddlename());
        type(By.name("lastname"),userData.getLastname());
        type(By.name("nickname"),userData.getNickname());
        type(By.name("company"), userData.getCompany());
        type(By.name("mobile"),userData.getMobile());

        //Проверка на наличие поля new_group, если creation=true ,значит при создании оно должно быть. Если эдит, то нет
        if (creation ){
            new Select(wd.findElement(By.name("new_group"))).selectByIndex(1);
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void initUserCreation() {
        click(By.linkText("add new"));
    }

    public void selectUser() {click(By.xpath("//td/input"));}

    public void editUser(){click(By.xpath("//img[@alt='Edit']"));}
    public void deleteUser() {click(By.xpath("//input[@value='Delete']"));}

    public void updateUser() {click(By.xpath("//div[@id='content']/form/input[22]"));}

    public void createUser(UserData user) {
        initUserCreation();
        fillUserForm(user,true);
        addUser();

    }

    public boolean isThereAUser() {
        return isElementPresent(By.xpath("//td/input"));
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}

