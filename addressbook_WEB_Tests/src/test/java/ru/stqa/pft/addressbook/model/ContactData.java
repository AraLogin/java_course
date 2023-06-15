package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name="addressbook")
@XStreamAlias("contact")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name ="id")
    private int id =Integer.MAX_VALUE;
    @Expose
    @Column(name ="firstname")
    private  String firstname;
    @Expose
    @Column(name ="lastname")
    private  String lastname;
    @Transient
    private String group;
    @Expose
    @Type(type="text")
    @Column(name ="address")
    private  String address;
    @Expose
    @Type(type="text")
    private  String email;
    @Expose
    @Type(type="text")
    private  String email2;
    @Expose
    @Type(type="text")
    private  String email3;
    @Expose
    @Column(name ="home")
    @Type(type="text")
    private  String homePhone;
    @Expose
    @Column(name ="mobile")
    @Type(type="text")
    private  String mobilePhone;
    @Expose
    @Column(name ="work")
    @Type(type="text")
    private  String workPhone;
    @Transient
    private  String allPhones;
    @Transient
    private  String allEmails;
    @Transient
    @Column(name ="photo")
    @Type(type="text")
    private String photo;

    public File getPhoto() {
        return new File( photo);
    }
    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }
    public int getId() {
        return id;

    }
    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }
    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }
    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }
    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }
    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }
    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }
    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }


    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public String getEmail2() {
        return email2;
    }
    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public String getWorkPhone() {
        return workPhone;
    }
    public String getAllPhones() {
        return allPhones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null && that.firstname != null && !Objects.equals(firstname, that.firstname)) { return false; }
        if (lastname != null && that.lastname != null && !Objects.equals(lastname, that.lastname)) { return false; }
        if (address != null && that.address != null && !Objects.equals(address, that.address)) { return false; }
        if (email != null && that.email != null && !Objects.equals(email, that.email)) { return false; }
        if (workPhone != null && that.workPhone != null && !Objects.equals(workPhone, that.workPhone)) { return false; }
        if (homePhone != null && that.homePhone != null && !Objects.equals(homePhone, that.homePhone)) { return false; }
        if (mobilePhone != null && that.mobilePhone != null && !Objects.equals(mobilePhone
                , that.mobilePhone)) { return false; }
        if (email2 != null && that.email2 != null && !Objects.equals(email2, that.email2)) { return false; }
        return email3 == null || that.email3 == null || Objects.equals(email3, that.email3);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                '}';
    }

}
