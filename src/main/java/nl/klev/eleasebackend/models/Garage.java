package nl.klev.eleasebackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "garages")
public class Garage {

    @Id
    @Column(nullable = false)
    private String garageName;
    @Enumerated(EnumType.STRING)
    private Country country;
    private String zipCode;

    @OneToMany(
            mappedBy = "garage",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )

    @JsonIgnore
    private List<Vehicle> vehicles;

    public enum Country {
        NETHERLANDS,
        BELGIUM;
    }

    public String getGarageName() {
        return garageName;
    }

    public Country getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setVehicles(List<Vehicle> vehicleList) {
        this.vehicles = vehicleList;
    }

}
