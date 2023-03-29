package nl.klev.eleasebackend.utilities;

import nl.klev.eleasebackend.dtos.GarageDto;
import nl.klev.eleasebackend.dtos.GarageInputDto;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.models.Garage;


public class GarageTransform {

    public static Garage toGarage(GarageInputDto garageInputDto) {
        var garage = new Garage();
        var countryToCheck = garageInputDto.getCountry();

        switch (countryToCheck) {
            case NETHERLANDS:
            case BELGIUM:
                break;
            default:
                throw new RecordNotFoundException("This country is not on the list!");
        }

        garage.setGarageName(garageInputDto.getGarageName());
        garage.setCountry(countryToCheck);
        garage.setZipCode(garageInputDto.getZipCode());

        return garage;
    }

    public static GarageDto toGarageDto(Garage garage) {

        var garageDto = new GarageDto();
        garageDto.setGarageName(garage.getGarageName());
        garageDto.setCountry(garage.getCountry());
        garageDto.setZipCode(garage.getZipCode());

        return garageDto;
    }

}
