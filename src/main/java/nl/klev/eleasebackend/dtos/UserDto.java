package nl.klev.eleasebackend.dtos;

import java.time.LocalDate;

public class UserDto {
    private Long id;
    private String fullName;
    private LocalDate dob;
    private String email;
    private String iban;
    private String password;
    private String username;
    private int drivingLicenseNumber;


    public UserDto() {
    }

    public UserDto(Long id, String fullName, LocalDate dob, String email, String iban, String password, String username, int drivingLicenseNumber) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.iban = iban;
        this.password = password;
        this.username = username;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
