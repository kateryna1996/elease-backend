package nl.klev.eleasebackend.repositories;

import nl.klev.eleasebackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
