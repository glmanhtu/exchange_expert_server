package com.exchange.restapi;

import com.exchange.backend.enums.MessageEnum;
import com.exchange.backend.persistence.domain.GoogleResponses;
import com.exchange.backend.persistence.domain.Message;
import com.exchange.backend.persistence.domain.SafeSearchAnnotation;
import com.exchange.backend.service.StorageService;
import com.exchange.exceptions.IllegalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.exchange.restapi.ResourceHandler.REST_API_RESOURCE;

/**
 * Created by glmanhtu on 2/21/17.
 */
@RestController
@RequestMapping(REST_API_RESOURCE)
public class ResourceHandler {
    public static final String REST_API_RESOURCE = "/resource";

    /**
     * The application logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceHandler.class);

    @Autowired
    private StorageService storageService;

    @ResponseBody
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("files") MultipartFile[] files) throws IOException {
        List<String> imageUrls = new ArrayList<>();
        //detect illegal images
        detectMultiIllegalImages(files);

        for (MultipartFile file : files) {
            imageUrls.add(storageService.saveImageFile(file));
        }
        return new ResponseEntity<Object>(imageUrls, HttpStatus.OK);
    }

    @GetMapping("/image/{filename:.+}")
    public ResponseEntity<?> getImage(@PathVariable("filename") String filename) throws IOException {
        File image = storageService.getImageFile(filename);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", new MimetypesFileTypeMap().getContentType(image));
        return new ResponseEntity<>(storageService.fileToByte(image), headers, HttpStatus.OK);
    }

    /**
     * Detects illegal images given by multipartfile[] files.
     *
     * @param files
     * @throws IOException
     */
    private void detectMultiIllegalImages(MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            detectIllegalImage(file);
        }
    }

    /**
     * Detects illegal image given by file path of image.
     *
     * @param file
     * @throws IOException
     * @see MessageEnum
     */
    private void detectIllegalImage(MultipartFile file) throws IOException {

        GoogleResponses result = storageService.detectImage(file);
        if (null != result) {
            SafeSearchAnnotation searchAnnotation = result.getResponses().get(0).getSafeSearchAnnotation();

            if (searchAnnotation.getAdult().equals("VERY_LIKELY")
                    || searchAnnotation.getAdult().equals("LIKELY")) {
                LOGGER.debug(MessageEnum.IMAGE_CONTAINTS_ADULT.getMessage());
                throw new IllegalException(new Message(MessageEnum.IMAGE_CONTAINTS_ADULT));
            } else if (searchAnnotation.getMedical().equals("VERY_LIKELY")
                    || searchAnnotation.getMedical().equals("LIKELY")) {
                LOGGER.debug(MessageEnum.IMAGE_CONTAINTS_MEDICAL.getMessage());
                throw new IllegalException(new Message(MessageEnum.IMAGE_CONTAINTS_MEDICAL));
            } else if (searchAnnotation.getViolence().equals("VERY_LIKELY")
                    || searchAnnotation.getViolence().equals("LIKELY")) {
                LOGGER.debug(MessageEnum.IMAGE_CONTAINTS_VIOLENCE.getMessage());
                throw new IllegalException(new Message(MessageEnum.IMAGE_CONTAINTS_VIOLENCE));
            } else if (searchAnnotation.getAdult().equals("POSSIBLE")
                    || searchAnnotation.getMedical().equals("POSSIBLE")
                    || searchAnnotation.getViolence().equals("POSSIBLE")) {
                LOGGER.debug("Image is possible contains {} or {} or {}",
                        MessageEnum.IMAGE_CONTAINTS_ADULT.getMessage(),
                        MessageEnum.IMAGE_CONTAINTS_MEDICAL.getMessage(),
                        MessageEnum.IMAGE_CONTAINTS_VIOLENCE.getMessage());
                //add to queue;
            }
        }

    }
}
