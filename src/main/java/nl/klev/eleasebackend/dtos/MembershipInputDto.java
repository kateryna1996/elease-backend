package nl.klev.eleasebackend.dtos;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class MembershipInputDto {

    @NotNull
    @Valid
    private String type;
    @NotNull
    @FutureOrPresent(message = "It cannot be a past date")
    private LocalDate membershipStartDate;
    @NotNull
    private boolean parkingIncluded;

    public MembershipInputDto() {
    }

    public String getType() {
        return type;
    }

    public LocalDate getMembershipStartDate() {
        return membershipStartDate;
    }

    public boolean isParkingIncluded() {
        return parkingIncluded;
    }

    public void setType(String name) {
        this.type = name;
    }

    public void setMembershipStartDate(LocalDate membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public void setParkingIncluded(boolean parkingIncluded) {
        this.parkingIncluded = parkingIncluded;
    }
}
