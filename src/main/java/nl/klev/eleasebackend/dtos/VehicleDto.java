package nl.klev.eleasebackend.dtos;

import nl.klev.eleasebackend.models.Garage;
import nl.klev.eleasebackend.models.Vehicle;


public class VehicleDto {

    private Long vehicleId;
    private Vehicle.VehicleType type;
    private String brand;
    private String model;
//    private int quantity;
    private boolean isRented;
    private boolean drivingLicenseRequired;
    private String licenseNumber;
    private double distanceRangeWithoutCharge;
    private Garage garage;

//    license?
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

//    public int getQuantity() {
//        return quantity;
//    }

    public boolean isRented() {
        return isRented;
    }

    public boolean isDrivingLicenseRequired() {
        return drivingLicenseRequired;
    }

    public double getDistanceRangeWithoutCharge() {
        return distanceRangeWithoutCharge;
    }

    public String getLicenseNumber() {
        return licenseNumber;
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

//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

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

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setGarage(Garage garage) {
        this.garage = garage;
    }
}
