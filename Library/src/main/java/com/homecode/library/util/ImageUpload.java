package com.homecode.library.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.*;

@Component
public class ImageUpload {

    private final Path root = Paths.get("C:\\Users\\zabra\\Desktop\\JavaWeb\\Diploma05\\Soft_uni_FinalProject_Model3d\\Customer\\src\\main\\resources\\static\\images\\product");

    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }


    public void uploadImage(MultipartFile imageModel) {
        try {
            Files.copy(imageModel.getInputStream(), this.root.resolve(imageModel.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A image of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }

}
