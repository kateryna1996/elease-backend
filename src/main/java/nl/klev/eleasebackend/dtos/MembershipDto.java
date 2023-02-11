package nl.klev.eleasebackend.dtos;


import java.time.LocalDate;

public class MembershipDto {

    private Long id;
    private String name;
    private String type;
    private LocalDate membershipStartDate;
    private LocalDate membershipEndDate;
    private double costs;
    private boolean parkingIncluded;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getMembershipStartDate() {
        return membershipStartDate;
    }

    public LocalDate getMembershipEndDate() {
        return membershipEndDate;
    }

    public double getCosts() {
        return costs;
    }

    public String getType() {
        return type;
    }

    public boolean isParkingIncluded() {
        return parkingIncluded;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembershipStartDate(LocalDate membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public void setMembershipEndDate(LocalDate membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParkingIncluded(boolean parkingIncluded) {
        this.parkingIncluded = parkingIncluded;
    }
}
