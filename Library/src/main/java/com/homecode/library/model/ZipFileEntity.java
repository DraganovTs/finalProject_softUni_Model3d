package com.homecode.library.model;

import jakarta.persistence.*;

@Entity
@Table(name = "zip_files")
public class ZipFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fileName;
    private String contentType;
    @Lob
    @Column(length = Integer.MAX_VALUE)
    private byte[] fileData;

    public ZipFileEntity() {
    }

    public Long getId() {
        return id;
    }

    public ZipFileEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public ZipFileEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public ZipFileEntity setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public ZipFileEntity setFileData(byte[] fileData) {
        this.fileData = fileData;
        return this;
    }
}
