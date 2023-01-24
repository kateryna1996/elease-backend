package nl.klev.eleasebackend.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserInputDto {

    @Size(min = 2, max = 20, message = "Your name cannot be shorter that 2 letters and longer than 20 characters")
    private String firstName;
    @NotNull(message = "The last name is required")
    private String lastName;
    @Past
    private LocalDate dob;

    public UserInputDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
