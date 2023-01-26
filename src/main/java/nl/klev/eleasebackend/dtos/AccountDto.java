package nl.klev.eleasebackend.dtos;

import java.time.LocalDate;

public class AccountDto {

    private Long accountId;
    private String fullName;
    private LocalDate dob;
    private String iban;
    private int drivingLicenseNumber;

    public AccountDto() {
    }

    public AccountDto(Long accountId, String fullName, LocalDate dob, String iban, int drivingLicenseNumber) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.dob = dob;
        this.iban = iban;
        this.drivingLicenseNumber = drivingLicenseNumber;
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
}
