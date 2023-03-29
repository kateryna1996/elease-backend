package nl.klev.eleasebackend.dtos;

import nl.klev.eleasebackend.models.Garage;
import nl.klev.eleasebackend.models.Vehicle;


public class VehicleDto {

    private Long vehicleId;
    private Vehicle.VehicleType type;
    private String brand;
    private String model;
    private boolean isRented;
    private boolean drivingLicenseRequired;
    private double distanceRangeWithoutCharge;
    private Garage garage;

    public VehicleDto() {
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public Vehicle.VehicleType getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public boolean isRented() {
        return isRented;
    }

    public boolean isDrivingLicenseRequired() {
        return drivingLicenseRequired;
    }

    public double getDistanceRangeWithoutCharge() {
        return distanceRangeWithoutCharge;
    }

    public Garage getGarage() {
        return garage;
    }

    public void setType(Vehicle.VehicleType type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public void setDrivingLicenseRequired(boolean drivingLicenseRequired) {
        this.drivingLicenseRequired = drivingLicenseRequired;
    }

    public void setDistanceRangeWithoutCharge(double distanceRangeWithoutCharge) {
        this.distanceRangeWithoutCharge = distanceRangeWithoutCharge;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }
}
