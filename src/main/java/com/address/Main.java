package com.address;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Address Book Program");
        AddressBookSystem addressBookSystem = new AddressBookSystem();
        AddressBook addressBook1 = new AddressBook();

        System.out.println("Adding contacts to AddressBook1");
        addressBook1.insertContact(sc);
        addressBookSystem.writeAddressBookToFile("AddressBook1.dat", addressBook1);
        AddressBook readFromFile = addressBookSystem.readAddressBookFromFile("AddressBook1.dat");
        displaySearchResult("Address Book 1", readFromFile.contactList);

        addressBookSystem.writeAddressBookToFileCSV("AddressBook1.csv", addressBook1);
        AddressBook readFromFileCSV = addressBookSystem.readAddressBookFromFileCSV("AddressBook1.csv");
        displaySearchResult("Address Book 1", readFromFileCSV.contactList);

        addressBookSystem.writeAddressBookToFileJSON("AddressBook1.json", addressBook1);
        AddressBook readFromFileJSON = addressBookSystem.readAddressBookFromFileJSON("AddressBook1.json");
        displaySearchResult("Address Book 1", readFromFileJSON.contactList);
        // addressBook1.askForEditContact(sc);

        AddressBook addressBook2 = new AddressBook();
        System.out.println("Adding contacts to AddressBook2");
        addressBook2.insertContact(sc);
        // addressBook2.askForEditContact(sc);

        addressBookSystem.addAddressBook("Address book 1", addressBook1);
        addressBookSystem.addAddressBook("Address book 2", addressBook2);
        // AddressBook addressBook = new AddressBook();

        // Use case: Adding contacts with duplicate check
        // addressBook.insertContact(sc);

        // Display all contacts in the address book
        System.out.println("All Contacts:");
        addressBook1.contactList.forEach(Contact::printContact);

        // UC 7: Duplicate Check
        if (addressBook1.checkForDuplicate()) {
            System.out.println("Duplicate found in Address Book 1.");
        } else {
            System.out.println("No duplicate found in Address Book 1.");
        }

        // UC 8: Search Person in City or State across multiple AddressBooks
        System.out.print("Enter the city or state to search: ");
        String searchLocation = sc.nextLine();

        // Search in the address book
        for (AddressBook addressBook : addressBookSystem.getAddressBooks().values()) {
            List<Contact> result = addressBook.searchPersonInCityOrState(searchLocation);
            displaySearchResult("Address Book", result);
        }

        // System.out.println("Enter name of contact to delete");
        // String contactToDelete = sc.nextLine();
        // addressBook1.deleteContact(contactToDelete);

        // UC 9: View Persons by City or State
        // View persons by city
        System.out.print("Enter the city to view persons: ");
        String viewCity = sc.nextLine();
        for (AddressBook addressBook : addressBookSystem.getAddressBooks().values()) {
            List<Contact> personsByCity = addressBook.viewPersonsByCity(viewCity);
            displayViewResult("City", personsByCity);
        }

        // View persons by state
        System.out.print("Enter the state to view persons: ");
        String viewState = sc.nextLine();
        for (AddressBook addressBook : addressBookSystem.getAddressBooks().values()) {
            List<Contact> personsByState = addressBook.viewPersonsByState(viewState);
            displayViewResult("State", personsByState);
        }

        // UC 10: Count Persons by City or State
        System.out.print("Enter the city to count persons: ");
        String countCity = sc.nextLine();
        for (AddressBook addressBook : addressBookSystem.getAddressBooks().values()) {
            long countByCity = addressBook.countPersonsByCity(countCity);
            System.out.println("Number of persons in " + countCity + ": " + countByCity);
        }

        System.out.print("Enter the state to count persons: ");
        String countState = sc.nextLine();
        for (AddressBook addressBook : addressBookSystem.getAddressBooks().values()) {
            long countByState = addressBook.countPersonsByState(countState);
            System.out.println("Number of persons in " + countState + ": " + countByState);
        }

        // UC 11: Sort entries by Person's name
        addressBook1.sortByName();
        System.out.println("Sorted Contacts by Name:");
        addressBook1.contactList.forEach(System.out::println);

        // UC 12: Sort entries by City, State, or Zip
        System.out.println("1: Sort by Name");
        System.out.println("2: Sort by City");
        System.out.println("3: Sort by State");
        System.out.println("4: Sort by ZipCode");
        System.out.println("Enter You Choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Sorting by Name:");
                addressBook1.sortByName();
                break;
            case 2:
                System.out.println("Sorting by City:");
                addressBook1.sortByCity();
                break;
            case 3:
                System.out.println("Sorting by State:");
                addressBook1.sortByState();
                break;
            case 4:
                System.out.println("Sorting by Zip Code:");
                addressBook1.sortByZip();
                break;
        }
        addressBook1.contactList.forEach(System.out::println);

        // // UC 13: Write and Read from File
        // System.out.print("Enter the file name to write contacts: ");
        // String writeFileName = sc.nextLine();
        // addressBook1.writeToFile(writeFileName);

        // System.out.print("Enter the file name to read contacts: ");
        // String readFileName = sc.nextLine();
        // addressBook2.readFromFile(readFileName);
    }

    private static void displaySearchResult(String addressBookName, List<Contact> result) {
        System.out.println("Search result in " + addressBookName + ":");
        if (result.isEmpty()) {
            System.out.println("No matching contacts found.");
        } else {
            result.forEach(Contact::printContact);
        }
    }

    private static void displayViewResult(String category, List<Contact> result) {
        System.out.println("Persons in " + category + ":");
        if (result.isEmpty()) {
            System.out.println("No persons found.");
        } else {
            result.forEach(Contact::printContact);
        }
    }
}