package nl.klev.eleasebackend.dtos;

import nl.klev.eleasebackend.models.Membership;
import nl.klev.eleasebackend.models.User;
import nl.klev.eleasebackend.models.Vehicle;

import javax.swing.plaf.metal.MetalMenuBarUI;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.lang.reflect.Member;
import java.time.LocalDate;

public class AccountInputDto {

    @Size(min = 2, max = 30, message = "Your name cannot be shorter that 2 letters and longer than 30 characters")
    private String fullName;
    @Past
    private LocalDate dob;
    @NotNull
    @Size(min = 8, max = 15, message = "The IBAN field is mandatory, please fiil it in!")
    private String iban;
    private int drivingLicenseNumber;

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
