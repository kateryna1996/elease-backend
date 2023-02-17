package nl.klev.eleasebackend.repositories;

import nl.klev.eleasebackend.models.FileResponse;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<FileResponse, String> {
    FileResponse findByFileName(String filename);
}
