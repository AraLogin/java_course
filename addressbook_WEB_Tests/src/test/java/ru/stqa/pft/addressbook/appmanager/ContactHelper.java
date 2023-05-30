package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.UserData;

public class ContactHelper extends HelperBase{

    ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void addUser() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillUserForm(UserData userData) {
        type(By.name("firstname"),userData.getFirstname());
        type(By.name("middlename"),userData.getMiddlename());
        type(By.name("lastname"),userData.getLastname());
        type(By.name("nickname"),userData.getNickname());
        type(By.name("company"), userData.getCompany());
        type(By.name("mobile"),userData.getMobile());
    }

    public void initUserCreation() {
        click(By.linkText("add new"));
    }

    public void selectUserId() {click(By.xpath("//td/input"));}

    public void editUser(){click(By.xpath("//img[@alt='Edit']"));}
    public void deleteUser() {click(By.xpath("//input[@value='Delete']"));}

    public void updateUser() {click(By.xpath("//div[@id='content']/form/input[22]"));}
}