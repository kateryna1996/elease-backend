package nl.klev.eleasebackend.services;

import nl.klev.eleasebackend.exceptions.FileNotFoundException;
import nl.klev.eleasebackend.exceptions.FileSavingException;
import nl.klev.eleasebackend.models.FileUpload;
import nl.klev.eleasebackend.repositories.FileUploadRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;


@Service
public class FileUploadService {
    private final FileUploadRepository fileUploadRepository;

    private final Path root = Paths.get("uploads");

    public FileUploadService(FileUploadRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
    }

    public FileUpload saveFile(MultipartFile file){
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            if(filename.contains("...")){
                throw new FileSavingException("File contains invalid characters"+ filename);
            }
            FileUpload fileUpload = new FileUpload(filename, file.getContentType(), file.getBytes());
            return fileUploadRepository.save(fileUpload);
    } catch (IOException e) {
            throw  new FileSavingException("File was not stored ", e);
        }
    }

    public FileUpload getFile(String fileId) {
        if(fileUploadRepository.existsById(fileId)) {
            return fileUploadRepository.findById(fileId).get();
        } else {
            throw new FileNotFoundException("The file with the id " + fileId + " does not exist!");
        }
    }

    public List<FileUpload> getAllFiles(){
        return fileUploadRepository.findAll();
    }
}
