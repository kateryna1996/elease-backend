package nl.klev.eleasebackend.repositories;

import nl.klev.eleasebackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User>  findAllByUsernameContaining(String name);
    User  findByUsername(String name);
    User findByEmail(String email);
}
