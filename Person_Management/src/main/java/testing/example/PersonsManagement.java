package testing.example;

import java.sql.*;
import java.util.Scanner;

public class PersonsManagement  {
    private Connection connection;

    public PersonsManagement() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Class forName
        this.connection = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/personsmanagement", "root", "");
    }

    public Person createPerson() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first name:");
        String firstName = sc.nextLine();
        if (firstName.matches(".*\\d.*")) {
            throw new InvalidPersonNameException("Invalid name entry!");
        }
        System.out.println("Enter last name:");
        String lastName = sc.nextLine();
        if (lastName.matches(".*\\d.*")) {
            throw new InvalidPersonNameException("Invalid name entry!");
        }
        System.out.println("Enter Date of birth (in format: 'DD-MM-YYYY' ):");
        String dateOfBirth = sc.nextLine();
        String[] dateParts = dateOfBirth.split("-");
        // we have here three entries exceptions(could be more than 3 for more accuracy)
        if (dateOfBirth.length() != 10 || dateParts.length != 3 || !dateOfBirth.matches(".*\\d.*")) {
            throw new InvalidPersonNameException("Invalid date format!!");
        }

        // add gender by choosing the first letter
        System.out.println("Enter gender (m -> MALE, f -> FEMALE, o -> OTHER):");
        String input = sc.nextLine();
        Gender gender;
        if (input.equals("m")) {
            gender = Gender.MALE;
        } else if (input.equals("f")) {
            gender = Gender.FEMALE;
        } else if (input.equals("o")) {
            gender = Gender.OTHER;
        } else {
            throw new InvalidPersonNameException("Invalid Gender entered!!");
        }

        System.out.println("Enter address (in format: zipCode city streetName houseNumber):");
        String addressString = sc.nextLine();
        String[] addressParts = addressString.split(" ");
        if (addressParts.length != 4 || !addressParts[0].matches(".*\\d.*") ||
                !addressParts[3].matches(".*\\d.*")) {
            throw new InvalidPersonNameException("Invalid address format: " + addressString);
        }

        Person p = new Person(firstName, lastName, dateOfBirth, gender,
                new Address(Integer.parseInt(addressParts[0]), addressParts[1], addressParts[2],
                        Integer.parseInt(addressParts[3])));

        String sql = "INSERT INTO persons (firstName, lastName, dateOfBirth, gender, zipCode, city, streetName, houseNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, p.getFirstName());
            statement.setString(2, p.getLastName());
            statement.setString(3, p.getDateOfBirth());
            statement.setString(4, p.getGender().name());
            statement.setInt(5, p.getAddress().getZipCode());
            statement.setString(6, p.getAddress().getCity());
            statement.setString(7, p.getAddress().getStreetName());
            statement.setInt(8, p.getAddress().getHouseNumber());
            statement.executeUpdate();

            System.out.println("Person added: " + firstName + " " + lastName);
        }
        return p;
    }



    public String findPerson(String firstName, String lastName) throws Exception {
        String query = "SELECT * FROM persons WHERE firstName = ? AND lastName = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Found: " + firstName + " " + lastName);
            } else {
                System.out.println("Not found: "+firstName+" "+lastName);
            }
        }
        return query;
    }

    public String removePerson(String firstName, String lastName) throws Exception {
        String query = "DELETE FROM persons WHERE firstName = ? AND lastName = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.executeUpdate();
        }
        return query;
    }
}




