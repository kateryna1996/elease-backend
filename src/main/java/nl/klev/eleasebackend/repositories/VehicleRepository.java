package nl.klev.eleasebackend.repositories;

import nl.klev.eleasebackend.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
