package com.address;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AddressBookSystem {
    HashMap<String, AddressBook> addressBooks;

    HashMap<String, AddressBook> getAddressBooks() {
        return addressBooks;
    }

    public AddressBookSystem() {
        addressBooks = new HashMap<String, AddressBook>();
    }

    public void addAddressBook(String name, AddressBook addressBook) {
        addressBooks.put(name, addressBook);
    }

    public void writeAddressBookToFile(String fileName, AddressBook addressBook) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(addressBook);
            out.close();
            fileOut.close();
            System.out.println("AddressBook written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AddressBook readAddressBookFromFile(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            AddressBook addressBook = (AddressBook) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Contacts read from file successfully.");
            return addressBook;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeAddressBookToFileCSV(String fileName, AddressBook addressBook) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Contact contact : addressBook.getContactList()) {
                writer.write(contact.toCSV());
                writer.newLine();
            }
            System.out.println("AddressBook written to CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AddressBook readAddressBookFromFileCSV(String fileName) {
        AddressBook addressBook = new AddressBook();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contact contact = Contact.fromCSV(line);
                addressBook.addContact(contact);
            }
            System.out.println("Contacts read from CSV file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressBook;
    }

    public void writeAddressBookToFileJSON(String fileName, AddressBook addressBook) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(addressBook, writer);
            System.out.println("AddressBook written to JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AddressBook readAddressBookFromFileJSON(String fileName) {
        Gson gson = new Gson();

        AddressBook addressBook = null;

        try (FileReader reader = new FileReader(fileName)) {
            addressBook = gson.fromJson(reader, AddressBook.class);
            System.out.println("Contacts read from JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addressBook != null ? addressBook : new AddressBook();
    }
}
