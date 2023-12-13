package com.address;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact implements Serializable {
    private String firstName, lastName, address, city, state, zipCode, phoneNumber, email;

    public Contact() {
        this.firstName = "";
        this.lastName = "";
        this.address = "";
        this.city = "";
        this.state = "";
        this.zipCode = "";
        this.phoneNumber = "";
        this.email = "";
    }

    public Contact(String firstName, String lastName, String address, String city, String state,
            String zipCode, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Contact contact = (Contact) obj;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName);
    }

    @Override
    public String toString() {
        return "Contact: " +
                "First Name: " + firstName +
                ", Last Name: " + lastName +
                ", Address: " + address +
                ", City: " + city +
                ", State: " + state +
                ", Zip code: " + zipCode +
                ", Phone Number: " + phoneNumber +
                ", Email: " + email;
    }

    public String toCSV() {
        return firstName + "," + lastName + "," + address + "," + city + "," + state + "," + zipCode + "," + phoneNumber
                + "," + email;
    }

    public static Contact fromCSV(String csvString) {
        String[] parts = csvString.split(",");
        return new Contact(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]);
    }

    public Boolean inputContact(Scanner sc, AddressBook addressBook) {
        System.out.println("Enter your first name: ");
        this.firstName = getUserInput(sc, "[A-Z][a-zA-Z]{2,}+", "Invalid first name. Please enter a valid name.");

        System.out.println("Enter your last name: ");
        this.lastName = getUserInput(sc, "[A-Z][a-zA-Z]{2,}+", "Invalid last name. Please enter a valid name.");

        if (addressBook.isDuplicateContactFound(this.firstName + this.lastName)) {
            return false;
        }

        System.out.println("Enter your address: ");
        this.address = sc.nextLine().trim();

        System.out.println("Enter your city name: ");
        this.city = sc.nextLine().trim();

        System.out.println("Enter your state name: ");
        this.state = sc.nextLine().trim();

        System.out.println("Enter your zip code: ");
        this.zipCode = getUserInput(sc, "\\d{5}", "Invalid zip code. Please enter a valid 5-digit zip code.");

        System.out.println("Enter your phone number: ");
        this.phoneNumber = getUserInput(sc, "\\d{10}", "Invalid phone number. Please enter a valid 10-digit number.");

        System.out.println("Enter your email: ");
        this.email = getUserInput(sc, "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b",
                "Invalid email address. Please enter a valid email.");
        return true;
    }

    private String getUserInput(Scanner sc, String regex, String errorMessage) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        String userInput;

        do {
            userInput = sc.nextLine().trim();
            matcher = pattern.matcher(userInput);

            if (!matcher.matches()) {
                System.out.println(errorMessage);
                System.out.println("Please enter the correct format:");
            }
        } while (!matcher.matches());

        return userInput;
    }

    public void editContact(Scanner sc) {
        String temp = "";
        System.out.println("Edit Contact");
        System.out.println("Enter new address (" + this.address + "): ");
        temp = sc.nextLine();
        this.address = temp;
        System.out.println("Enter new city (" + this.city + "): ");
        temp = sc.nextLine();
        this.city = temp;
        System.out.println("Enter new state (" + this.state + "): ");
        temp = sc.nextLine();
        this.state = temp;
        System.out.println("Enter new zip code (" + this.zipCode + "): ");
        temp = sc.nextLine();
        this.zipCode = temp;
        System.out.println("Enter new phone number (" + this.phoneNumber + "): ");
        temp = sc.nextLine();
        this.phoneNumber = temp;
        System.out.println("Enter new email (" + this.email + "): ");
        temp = sc.nextLine();
        this.email = temp;
    }

    public void printContact() {
        System.out.println("Contact: ");
        System.out.println("First Name: " + this.firstName);
        System.out.println("Last Name: " + this.lastName);
        System.out.println("Address: " + this.address);
        System.out.println("City: " + this.city);
        System.out.println("State: " + this.state);
        System.out.println("Zip code: " + this.zipCode);
        System.out.println("Phone Number: " + this.phoneNumber);
        System.out.println("Email: " + this.email);
    }
}