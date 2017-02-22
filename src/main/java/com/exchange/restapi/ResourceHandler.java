package com.exchange.restapi;

import com.exchange.backend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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

    @Autowired
    private StorageService storageService;

    @ResponseBody
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("files") MultipartFile[] files) {
        List<String> imageUrls = new ArrayList<>();
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
}
