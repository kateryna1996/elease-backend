package nl.klev.eleasebackend.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Garage {

    @Id
    private String garageName;
    @Enumerated(EnumType.STRING)
    private Country country;
    private String zipCode;

    public Garage() {
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

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public enum Country {
        NETHERLANDS,
        BELGIUM;
    }
}
