package nl.klev.eleasebackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    private String brand;
    private String model;
    private boolean isRented = true;
    private boolean drivingLicenseRequired;
    private double distanceRangeWithoutCharge;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "garage_name")
    private Garage garage;

    @OneToOne(mappedBy = "vehicle",
            cascade=CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private Account account;

    public enum VehicleType {
        ELECTRIC_CAR,
        ELECTRIC_SCOOTER,
        ELECTRIC_BICYCLE;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public VehicleType getType() {
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

    public Account getAccount() {
        return account;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setType(VehicleType type) {
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

    public void setGarage(Garage garage) {
        this.garage = garage;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
