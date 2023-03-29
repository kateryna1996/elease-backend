package nl.klev.eleasebackend.repositories;

import nl.klev.eleasebackend.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
<<<<<<< HEAD

=======
>>>>>>> 56462df728c6a09a501a2d29398eb062bba4a715

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findVehiclesByBrand(String brand);
}
