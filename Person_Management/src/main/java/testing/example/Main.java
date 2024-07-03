package testing.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        PersonsManagement personsManagement = new PersonsManagement();
        // user now can use the program
        while (true) {
            System.out.println("choose any option to proceed: ");
            System.out.println("1. Add Person");
            System.out.println("2. find Person");
            System.out.println("3. remove Person");
            System.out.println("any other key. return");
            char option = sc.nextLine().charAt(0);

            switch (option) {
                case '1': // adding persons using user inputs
                    try {
                        personsManagement.createPerson();// call a method to create person Name
                    } catch (InvalidPersonNameException e) {
                        System.out.println("Error: " + e.getMessage());
                    } finally {
                        sc.nextLine();
                    }
                    break;
                case '2':
                    try {
                        //looking for a person by his first and last name
                        System.out.println("Enter first name:");
                        String firstName = sc.nextLine();
                        System.out.println("Enter last name:");
                        String lastName = sc.nextLine();
                        // call a method to find a person
                        personsManagement.findPerson(firstName, lastName);
                    } catch (Exception e) {
                        throw new NullPointerException();
                    }
                    sc.nextLine();
                    break;
                case '3':
                    try {
                        System.out.println("Enter the first name:");
                        String firstName = sc.nextLine();
                        System.out.println("Enter the last name:");
                        String lastName = sc.nextLine();
                        // call a method to delete a person
                        personsManagement.removePerson(firstName, lastName);
                    } catch (NullPointerException e) {
                        System.out.println("Error: " + e.getMessage());
                    } finally {
                        sc.nextLine();
                    }
                    break;
                default:
                    System.out.println("Returning to Main menu... ");
                    break;
            }
        }
    }
}
