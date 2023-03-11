package com.homecode.library.util;


import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ZipFileUpload {

    private final Path root = Paths.get("C:\\Users\\zabra\\Desktop\\JavaWeb\\Diploma05\\Soft_uni_FinalProject_Model3d\\Customer\\src\\main\\resources\\static\\upload");


    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public void uploadZipFile(MultipartFile zipModel) {
        try {
            Files.copy(zipModel.getInputStream(), this.root.resolve(zipModel.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A zip of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }


}
