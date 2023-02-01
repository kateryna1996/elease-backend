package nl.klev.eleasebackend.controllers;


import nl.klev.eleasebackend.models.FileResponse;
import nl.klev.eleasebackend.models.FileUpload;
import nl.klev.eleasebackend.services.FileUploadService;
import nl.klev.eleasebackend.utilities.WriteToFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("files")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        FileUpload fileUpload = fileUploadService.saveFile(file);
        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("files/download/").path(fileUpload.getFileId()).toUriString();

        return new FileResponse(fileUpload.getFileName(), fileUpload.getFileType(), fileUri);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
        FileUpload fileUpload = fileUploadService.getFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fileUpload.getFileName() + "\"").body(new ByteArrayResource(fileUpload.getFileData()));
    }

    @GetMapping("/allFiles")
    public ResponseEntity<List<FileResponse>> getFilesList() {
        List<FileResponse> fileUploads = fileUploadService.getAllFiles().stream().map(fileUpload -> {
            String fileUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/download/")
                    .path(fileUpload.getFileId())
                    .toUriString();
            return new FileResponse(fileUpload.getFileName(), fileUpload.getFileType(), fileUri);
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(fileUploads);
    }


}
