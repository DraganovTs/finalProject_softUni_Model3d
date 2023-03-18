package com.homecode.library.service;


import com.homecode.library.model.ImageFileEntity;
import com.homecode.library.model.ZipFileEntity;
import com.homecode.library.model.dto.FileDownloadModelDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface FileService {

    ImageFileEntity saveImageFile(String contentType, String fileName , byte[] fileData) throws IOException;

    ZipFileEntity saveZipFile(String contentType, String fileName , byte[] fileData) throws IOException;


    public Optional<FileDownloadModelDTO> getFileById(Long fileId);


}
