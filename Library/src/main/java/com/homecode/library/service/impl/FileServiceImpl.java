package com.homecode.library.service.impl;

import com.homecode.library.model.ImageFileEntity;
import com.homecode.library.model.ZipFileEntity;
import com.homecode.library.model.dto.FileDownloadModelDTO;
import com.homecode.library.repository.ImageFileRepository;
import com.homecode.library.repository.ZipFileRepository;
import com.homecode.library.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Service
public class FileServiceImpl implements FileService {

    private final ImageFileRepository imageFileRepository;
    private final ZipFileRepository zipFileRepository;


    @Autowired
    public FileServiceImpl(ImageFileRepository imageFileRepository, ZipFileRepository zipFileRepository) {
        this.imageFileRepository = imageFileRepository;
        this.zipFileRepository = zipFileRepository;
    }


    @Override
    public ImageFileEntity saveImageFile(String contentType, String fileName, byte[] fileData) throws IOException {


        ImageFileEntity fileImage = new ImageFileEntity()
                .setContentType(contentType)
                .setFileName(fileName)
                .setFileData(fileData);


        return this.imageFileRepository.save(fileImage);

    }


    @Override
    public ZipFileEntity saveZipFile(String contentType, String fileName, byte[] fileData) throws IOException {
        ZipFileEntity fileZip = new ZipFileEntity()
                .setContentType(fileName)
                .setFileName(contentType)
                .setFileData(fileData);


        return this.zipFileRepository.save(fileZip);
    }


    @Override
    public Optional<FileDownloadModelDTO> getFileById(Long fileId) {

        var file = this.imageFileRepository.findById(fileId).orElseThrow();

        return Optional.of(new FileDownloadModelDTO(
                file.getFileData(),
                file.getContentType(),
                file.getFileName()
        ));


    }

    @Override
    public Optional<FileDownloadModelDTO> getFileZipFileById(Long fileId) {
        var file = this.zipFileRepository.findById(fileId).orElseThrow();

        return Optional.of(new FileDownloadModelDTO(
                file.getFileData(),
                file.getContentType(),
                file.getFileName()
        ));
    }
}
