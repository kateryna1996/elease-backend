package nl.klev.eleasebackend.models;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue
    private Long accountId;
    private String fullName;
    private LocalDate dob;
    private String iban;
    private int drivingLicenseNumber;

    @OneToOne(mappedBy = "account")
    @JoinColumn(name = "account_id")
    private User user;

    public Account() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getIban() {
        return iban;
    }

    public int getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public User getUser() {
        return user;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setDrivingLicenseNumber(int drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
