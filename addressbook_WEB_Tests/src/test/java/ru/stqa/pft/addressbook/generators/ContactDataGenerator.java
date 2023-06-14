package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        save(contacts,new File(file));
    }
    private void save(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n",contact.getFirstname(),contact.getLastname(),contact.getAddress()
            ,contact.getEmail(),contact.getEmail2(),contact.getEmail3(),contact.getHomePhone(),contact.getMobilePhone(),
                    contact.getWorkPhone()));
        }
        writer.close();
    }

    private List<ContactData> generateContacts (int count){
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData().withFirstname(String.format("test %s",i))
                    .withLastname(String.format("Testov %s",i))
                    .withAddress(String.format("Street %s",i))
                    .withEmail(String.format("null%s@gmail.com",i))
                    .withEmail2(String.format("null%s@yandex.com",i))
                    .withEmail3(String.format("null%s@mail.com",i))
                    .withHomePhone(String.format("55-55-5%s@gmail.com",i))
                    .withMobilePhone(String.format("+77775564465%s",i))
                    .withWorkPhone(String.format("2344565%s",i)));
        }
        return contacts;
    }
}

