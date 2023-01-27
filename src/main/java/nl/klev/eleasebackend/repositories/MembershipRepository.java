package nl.klev.eleasebackend.repositories;

import nl.klev.eleasebackend.models.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
}
