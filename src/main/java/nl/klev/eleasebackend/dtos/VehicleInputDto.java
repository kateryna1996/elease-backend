package nl.klev.eleasebackend.dtos;

import nl.klev.eleasebackend.models.Vehicle;

import javax.validation.constraints.NotNull;

public class VehicleInputDto {

    @NotNull
    private Vehicle.VehicleType type;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    private int quantity;
    private boolean isRented;
    private boolean drivingLicenseRequired;
    private double distanceRangeWithoutCharge;

    public VehicleInputDto() {
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

    public int getQuantity() {
        return quantity;
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

    public void setType(Vehicle.VehicleType type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
}
