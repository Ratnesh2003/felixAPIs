package com.felix.felixapis.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    @Value("${project.image}")
    private String UPLOAD_DIR;


    public boolean uploadFile(MultipartFile file) {
        boolean fileUploaded = false;
        try {
            Files.copy(
                    file.getInputStream(),
                    Paths.get(UPLOAD_DIR + File.separator + file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING
            );
            fileUploaded = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return fileUploaded;
    }
}
