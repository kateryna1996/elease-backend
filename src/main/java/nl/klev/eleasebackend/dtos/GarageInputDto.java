package nl.klev.eleasebackend.dtos;

import nl.klev.eleasebackend.models.Garage;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GarageInputDto {

    @NotNull
    @Size(min = 4, max = 25, message = "The name should be 4-25 characters long")
    private String garageName;
    @NotNull
    private Garage.Country country;
    @NotNull
    private String zipCode;

    public String getGarageName() {
        return garageName;
    }

    public Garage.Country getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setGarageName(String garageName) {
        this.garageName = garageName;
    }

    public void setCountry(Garage.Country country) {
        this.country = country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
