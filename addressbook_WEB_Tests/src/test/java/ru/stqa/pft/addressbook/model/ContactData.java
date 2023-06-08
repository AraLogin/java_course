package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String firstname;
    private final String middlename;
    private final String lastname;
    private final String nickname;
    private final String company;
    private final String mobile;


    public ContactData(String firstname, String midlename, String lastname, String nickname, String company,
                       String mobile) {
        this.firstname = firstname;
        this.middlename = midlename;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.mobile = mobile;

    }
    public String getFirstname() {
        return firstname;
    }
    public String getMiddlename() {
        return middlename;
    }
    public String getLastname() {
        return lastname;
    }
    public String getNickname() {
        return nickname;
    }
    public String getCompany() {
        return company;
    }
    public String getMobile() {
        return mobile;
    }
}
