package nl.klev.eleasebackend.repositories;

import nl.klev.eleasebackend.models.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, String> {
}
