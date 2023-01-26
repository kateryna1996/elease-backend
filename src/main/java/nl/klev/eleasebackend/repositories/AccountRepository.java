package nl.klev.eleasebackend.repositories;

import nl.klev.eleasebackend.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByFullName(String name);
    Account findAccountByDrivingLicenseNumber(int drivingLicense);
}
