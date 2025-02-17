package org.example;
import lombok.Builder;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Getter
@EqualsAndHashCode
@ToString
@JsonDeserialize(builder = Person.PersonBuilder.class)
public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;

    private Person(String id, String firstName, String lastName, Integer age, String gender) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    @Builder
    public static Person build(String id, String firstName, String lastName, Integer age, String gender) {
        return new Person(id, firstName, lastName, age, gender);
    }
}
