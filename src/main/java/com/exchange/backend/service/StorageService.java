package com.exchange.backend.service;

import com.exchange.backend.persistence.domain.GoogleRequest;
import com.exchange.backend.persistence.domain.GoogleRequests;
import com.exchange.backend.persistence.domain.GoogleResponses;
import com.exchange.backend.persistence.domain.GoogleImage;
import com.exchange.backend.persistence.domain.GoogleFeature;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by glmanhtu on 2/21/17.
 */
@Service
public class StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageService.class);

    private static final String UPLOADED_DIR = "resource";

    private static final String IMAGE_DIR = "image";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${google.api.key}")
    private String googleApiKey;

    public File getImageFile(String fileName) {
        return Paths.get(UPLOADED_DIR, IMAGE_DIR, fileName).toFile();
    }

    /**
     * Convert file to byte
     *
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

    public GoogleResponses detectImage(MultipartFile file) throws IOException {

        // Reads the image file into memory
        byte[] data = file.getBytes();
        String base64String = Base64.getEncoder().encodeToString(data);

        GoogleImage image = new GoogleImage(base64String);

        GoogleFeature feature = new GoogleFeature("SAFE_SEARCH_DETECTION");

        GoogleRequest request = new GoogleRequest(image, feature);

        GoogleRequests googleRequests = new GoogleRequests(request);

        ResponseEntity<GoogleResponses> response = restTemplate.postForEntity("https://vision.googleapis.com/v1/images:annotate?key={key}",
                googleRequests, GoogleResponses.class, googleApiKey);

       return response.getBody();
    }

}
