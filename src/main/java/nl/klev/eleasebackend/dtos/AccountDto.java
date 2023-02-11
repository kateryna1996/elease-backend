package nl.klev.eleasebackend.dtos;

import nl.klev.eleasebackend.models.Membership;
import nl.klev.eleasebackend.models.User;
import nl.klev.eleasebackend.models.Vehicle;

import java.time.LocalDate;

public class AccountDto {

    private Long accountId;
    private String fullName;
    private LocalDate dob;
    private String iban;
    private int drivingLicenseNumber;

    private User user;
    private Membership membership;
    private Vehicle vehicle;

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

    public Membership getMembership() {
        return membership;
    }

    public Vehicle getVehicle() {
        return vehicle;
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

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
