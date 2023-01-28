package nl.klev.eleasebackend.repositories;

import nl.klev.eleasebackend.models.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarageRepository extends JpaRepository<Garage, String> {
}
