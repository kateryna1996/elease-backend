package nl.klev.eleasebackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long membershipId;
    private String name;
    private String type;
    private LocalDate membershipStartDate;
    private LocalDate membershipEndDate;
    private double costs;
    private boolean parkingIncluded;


    @OneToOne(
            mappedBy = "membership",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )

    @JsonIgnore
    private Account account;

    public Membership() {
    }

    public Membership(Long membershipId, String name, String type, LocalDate membershipStartDate, LocalDate membershipEndDate, double costs, boolean parkingIncluded, Account account) {
        this.membershipId = membershipId;
        this.name = name;
        this.type = type;
        this.membershipStartDate = membershipStartDate;
        this.membershipEndDate = membershipEndDate;
        this.costs = costs;
        this.parkingIncluded = parkingIncluded;
        this.account = account;
    }


    public Long getMembershipId() {
        return membershipId;
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

    public Account getAccount() {
        return account;
    }

    public boolean isParkingIncluded() {
        return parkingIncluded;
    }

    public void setMembershipId(Long id) {
        this.membershipId = id;
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

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setParkingIncluded(boolean parkingIncluded) {
        this.parkingIncluded = parkingIncluded;
    }
}
