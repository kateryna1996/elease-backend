package nl.klev.eleasebackend.dtos;

import nl.klev.eleasebackend.models.Garage;

public class GarageDto {

    private String garageName;
    private Garage.Country country ;
    private String zipCode;

    public GarageDto() {
    }

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
