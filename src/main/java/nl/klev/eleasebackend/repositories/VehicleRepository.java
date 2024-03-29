package nl.klev.eleasebackend.repositories;

import nl.klev.eleasebackend.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findVehiclesByBrand(String brand);
}
