package nl.klev.eleasebackend.models;

public class FileResponse {
    private String fileName;
    private String fileType;
    private String fileUri;

    public FileResponse() {
    }

    public FileResponse(String fileName, String fileType, String fileUri) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileUri = fileUri;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }
}