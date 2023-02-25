package nl.klev.eleasebackend.utilities;

import nl.klev.eleasebackend.dtos.VehicleDto;
import nl.klev.eleasebackend.dtos.VehicleInputDto;
import nl.klev.eleasebackend.exceptions.RecordNotFoundException;
import nl.klev.eleasebackend.models.Vehicle;

public class VehicleTransform {

    public static Vehicle toVehicle(VehicleInputDto vehicleInputDto) {
        var vehicle = new Vehicle();
        var type = vehicleInputDto.getType();
        switch (type){
            case ELECTRIC_CAR:
            case ELECTRIC_SCOOTER:
                vehicle.setDrivingLicenseRequired(true);
                break;
            case ELECTRIC_BICYCLE:
                break;
            default:
                throw new RecordNotFoundException("You need to choose the type");
        }
        vehicle.setBrand(vehicleInputDto.getBrand());
        vehicle.setModel(vehicleInputDto.getModel());
        vehicle.setDistanceRangeWithoutCharge(vehicleInputDto.getDistanceRangeWithoutCharge());
        vehicle.setType(type);
        if (vehicle.getAccount()!= null) {
            vehicle.setRented(true);
        }
        return vehicle;
    }

    public static VehicleDto toVehicleDto(Vehicle vehicle){
        var vehicleDto = new VehicleDto();

        vehicleDto.setVehicleId(vehicle.getVehicleId());
        vehicleDto.setType(vehicle.getType());
        vehicleDto.setBrand(vehicle.getBrand());
        vehicleDto.setModel(vehicle.getModel());
        vehicleDto.setDrivingLicenseRequired(vehicle.isDrivingLicenseRequired());
        vehicleDto.setDistanceRangeWithoutCharge(vehicle.getDistanceRangeWithoutCharge());
        if(vehicle.getGarage() != null ) {
            vehicleDto.setGarage(vehicle.getGarage());
        }
        if(vehicle.getAccount() != null) {
            vehicleDto.setRented(true);
        }

        return vehicleDto;
    }
}
