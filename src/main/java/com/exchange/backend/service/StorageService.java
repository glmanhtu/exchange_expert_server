package com.exchange.backend.service;


import com.google.cloud.vision.spi.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.SafeSearchAnnotation;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.protobuf.ByteString;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.System.out;

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

    public boolean detectInvaliteImage(String filePath) throws IOException {

        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.SAFE_SEARCH_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        BatchAnnotateImagesResponse response =
                ImageAnnotatorClient.create().batchAnnotateImages(requests);
        List<AnnotateImageResponse> responses = response.getResponsesList();

        for (AnnotateImageResponse res : responses) {
            if (res.hasError()) {
                out.printf("Error: %s\n", res.getError().getMessage());
                return false;
            }

            // For full list of available annotations, see http://g.co/cloud/vision/docs
            SafeSearchAnnotation annotation = res.getSafeSearchAnnotation();
            out.printf(
                    "adult: %s\nmedical: %s\nspoofed: %s\nviolence: %s\n",
                    annotation.getAdult(),
                    annotation.getMedical(),
                    annotation.getSpoof(),
                    annotation.getViolence());
        }

        return false;
    }
}
