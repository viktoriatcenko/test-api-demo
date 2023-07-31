package ru.maxima.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

public class PersonDTO {

    @NotEmpty(message = "Name should not to be empty")
    @Size(min = 3, max = 30, message = "Name should be between 2 and 30 characters")
    public String name;

    @Min(value = 1, message = "Age should be more than 0")
    public int age;

    @NotEmpty(message = "Email should not to be empty")
    @Email(message = "Email should be valid")
    public String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
