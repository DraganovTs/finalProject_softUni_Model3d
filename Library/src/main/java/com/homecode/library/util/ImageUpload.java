package com.homecode.library.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageUpload {

    private String UPLOAD_FOLDER = "C:\\Users\\zabra\\Desktop\\JavaWeb\\Diploma05\\Soft_uni_FinalProject_Model3d\\Customer\\src\\main\\resources\\static\\images\\product";

    public boolean uploadImage(MultipartFile imageModel) {
        boolean isUpload = false;
        try {
            Files.copy(imageModel.getInputStream(), Paths.get(UPLOAD_FOLDER + File.separator,
                    imageModel.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);

            isUpload = true;
        } catch (Exception e) {

        }
        return isUpload;
    }

}
