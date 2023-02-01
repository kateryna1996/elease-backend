package nl.klev.eleasebackend.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileUpload {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String fileId;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] fileData;

    public FileUpload() {
    }

    public FileUpload( String fileName, String fileType, byte[] fileData) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileData = fileData;
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
