import org.example.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testPersonCreationValid() {
        // Valid inputs for creating a Person
        Person person = Person.builder()
                .id("IN123")
                .firstName("Aarav")
                .lastName("Sharma")
                .age(30)
                .gender("Male")
                .build();

        // Assert that the object is created successfully
        assertNotNull(person);
        assertEquals("IN123", person.getId());
        assertEquals("Aarav", person.getFirstName());
        assertEquals("Sharma", person.getLastName());
        assertEquals(30, person.getAge());
        assertEquals("Male", person.getGender());
    }

    @Test
    void testPersonCreationInvalidId() {
        // Invalid ID should throw an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Person.builder()
                    .id(null) // Null ID
                    .firstName("Aarav")
                    .lastName("Sharma")
                    .age(30)
                    .gender("Male")
                    .build();
        });
        assertEquals("ID cannot be null", exception.getMessage());
    }

    @Test
    void testPersonCreationInvalidFirstName() {
        // Invalid first name (empty) should throw an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Person.builder()
                    .id("IN123")
                    .firstName("") // Empty first name
                    .lastName("Sharma")
                    .age(30)
                    .gender("Male")
                    .build();
        });
        assertEquals("First name cannot be null or blank", exception.getMessage());
    }

    @Test
    void testPersonCreationInvalidAge() {
        // Invalid age (negative) should throw an exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Person.builder()
                    .id("IN123")
                    .firstName("Aarav")
                    .lastName("Sharma")
                    .age(-5) // Negative age
                    .gender("Male")
                    .build();
        });
        assertEquals("Age cannot be negative", exception.getMessage());
    }
}
