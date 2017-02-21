package com.exchange.backend.service;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by glmanhtu on 2/21/17.
 */
@Service
public class StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);

    private static final String UPLOADED_DIR = "resource";

    private static final String IMAGE_DIR = "image";

    public File getImageFile(String fileName) {
        return Paths.get(UPLOADED_DIR, IMAGE_DIR, fileName).toFile();
    }

    /**
     * Convert file to byte
     * @param file File
     * @return byte of file
     */
    public byte[] fileToByte(File file) throws IOException {
        return IOUtils.toByteArray(new FileInputStream(file));
    }

    public String saveImageFile(MultipartFile file) {
        int lastDotIndex = file.getOriginalFilename().lastIndexOf('.');
        String fileExtension = file.getOriginalFilename().substring(lastDotIndex - 1);
        String fileName = UUID.randomUUID().toString() + "." + fileExtension;
        File destination = Paths.get(UPLOADED_DIR, IMAGE_DIR, fileName).toFile();
        if (!destination.exists()) {
            destination.getParentFile().mkdirs();
        }
        try {
            FileCopyUtils.copy(file.getBytes(), destination);
            return UPLOADED_DIR + "/" + IMAGE_DIR + "/" + fileName;
        } catch (IOException e) {
            LOGGER.error("IOException, file: " + destination.getAbsolutePath());
            throw new RuntimeException(e);
        }
    }
}
