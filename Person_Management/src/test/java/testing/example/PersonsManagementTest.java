package testing.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PersonsManagementTest {

    private static PersonsManagement personsManagement;

    @BeforeAll
    static void setUp()  {
        try {
            personsManagement = new PersonsManagement();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    void testCreatePerson_ValidInput() {
        try {
            // Test createPerson with valid input
            Person person = personsManagement.createPerson();
            assertNotNull(person);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    void testCreatePerson_InvalidNameInput() {
        try {
            // Test createPerson with invalid name input
            assertThrows(InvalidPersonNameException.class, () -> personsManagement.createPerson());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    void testCreatePerson_InvalidDateInput() {
        try {
            // Test createPerson with invalid date input
            assertThrows(InvalidPersonNameException.class, () -> personsManagement.createPerson());
        } catch (Exception e) {
           e.getStackTrace();
        }
    }

    @Test
    void testFindPerson_Found() {
        try {
            // Test findPerson with existing person
            String firstName = "John";
            String lastName = "Cena";
            String result = personsManagement.findPerson(firstName, lastName);
            assertNotNull(result);
            assertTrue(result.contains(firstName) && result.contains(lastName));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    void testFindPerson_NotFound() {
        try {
            // Test findPerson with non-existing person
            String firstName = "Any";
            String lastName = "Name";
            String result = personsManagement.findPerson(firstName, lastName);
            assertNotNull(result);
            assertFalse(result.contains(firstName) && result.contains(lastName));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    void testRemovePerson_Successful() {
        try {
            // Test removePerson with existing person
            String firstName = "John";
            String lastName = "Cena";
            String result = personsManagement.removePerson(firstName, lastName);
            assertNotNull(result);
            assertTrue(result.contains(firstName) && result.contains(lastName));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    void testRemovePerson_NotFound() {
        try {
            // Test removePerson with non-existing person
            String firstName = "Some";
            String lastName = "Body";
            String result = personsManagement.removePerson(firstName, lastName);
            assertNotNull(result);
            assertFalse(result.contains(firstName) && result.contains(lastName));
        } catch (Exception e) {
           e.getStackTrace();
        }
    }
}
