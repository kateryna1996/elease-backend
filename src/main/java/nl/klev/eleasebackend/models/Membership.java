package nl.klev.eleasebackend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Membership {

    @Id
    @GeneratedValue
    private Long membershipId;
    private String name;
    private String type;
    private LocalDate membershipStartDate;
    private LocalDate membershipEndDate;
    private double costs;

    public Membership() {
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
}
