package springboot_demo1.demo.domain;

public class uploadFile {
    public String uploadFileName;
    public long uploadFileSize;
    public String uploadFilePath;

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public long getUploadFileSize() {
        return uploadFileSize;
    }

    public void setUploadFileSize(long uploadFileSize) {
        this.uploadFileSize = uploadFileSize;
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    @Override
    public String toString() {
        return "uploadFile{" +
                "uploadFileSize=" + uploadFileSize +
                '}';
    }
}
