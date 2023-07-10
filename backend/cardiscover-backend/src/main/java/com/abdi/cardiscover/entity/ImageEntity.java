package com.abdi.cardiscover.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class ImageEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String filePath;
    private String fileName;
    private Long fileSize;

    public ImageEntity(String filePath, String fileName, Long fileSize) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Long getFileSize() {
        return fileSize;
    }
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    @Override
    public String toString() {
        return "ImageEntity [filePath=" + filePath + ", fileName=" + fileName + ", fileSize=" + fileSize + "]";
    }    
}
