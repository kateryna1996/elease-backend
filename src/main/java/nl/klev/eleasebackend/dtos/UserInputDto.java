package nl.klev.eleasebackend.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserInputDto {

    @Size(min = 2, max = 30, message = "Your name cannot be shorter that 2 letters and longer than 30 characters")
    private String fullName;
    @Past
    private LocalDate dob;
    @NotNull
    private String email;
    private String iban;
    private String password;
    private String username;
//    @Size(min = 10, max = 10, message = "Driving license number is 10 characters long, please check it again")
    private int drivingLicenseNumber;

    @Size(min = 2, max = 20, message = "Your name cannot be shorter that 2 letters and longer than 20 characters")
    private String firstName;
    @NotNull(message = "The last name is required")
    private String lastName;

    public UserInputDto() {
    }

    public UserInputDto(String fullName, LocalDate dob, String email, String iban, String password, String username, int drivingLicenseNumber) {
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.iban = iban;
        this.password = password;
        this.username = username;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(int drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }
}
