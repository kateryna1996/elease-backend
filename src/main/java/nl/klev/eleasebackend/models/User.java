package nl.klev.eleasebackend.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue
    private Long id;
<<<<<<< HEAD
    private String fullName;
    private LocalDate dob;
    private String email;
    private String iban;
    private String password;
    private String username;
    private int drivingLicenseNumber;
=======
    private String firstName;
    private String lastName;
    private LocalDate dob;
>>>>>>> e9f3d9ece8e612539d6c0f67c8992f44afb456c0

    public User() {
    }

<<<<<<< HEAD
    public User(Long id, String fullName, LocalDate dob, String email, String iban, String password, String username, int drivingLicenseNumber) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.iban = iban;
        this.password = password;
        this.username = username;
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

=======
>>>>>>> e9f3d9ece8e612539d6c0f67c8992f44afb456c0
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
=======
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
>>>>>>> e9f3d9ece8e612539d6c0f67c8992f44afb456c0
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
<<<<<<< HEAD

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String IBAN) {
        this.iban = IBAN;
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
=======
>>>>>>> e9f3d9ece8e612539d6c0f67c8992f44afb456c0
}
