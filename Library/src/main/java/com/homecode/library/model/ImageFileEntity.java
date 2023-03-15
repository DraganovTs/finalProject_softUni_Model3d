package com.homecode.library.model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class ImageFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fileName;
    private String contentType;
    @Lob
    @Column(length = Integer.MAX_VALUE)
    private byte[] fileData;

    public String getContentType() {
        return contentType;
    }

    public ImageFileEntity setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public ImageFileEntity() {
    }

    public Long getId() {
        return id;
    }

    public ImageFileEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public ImageFileEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }


    public byte[] getFileData() {
        return fileData;
    }

    public ImageFileEntity setFileData(byte[] data) {
        this.fileData = data;
        return this;
    }
}
