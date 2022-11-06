package com.felix.felixapis.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileUploadHelper {
    @Value("${project.image}")
    private String UPLOAD_DIR;


    public boolean uploadFile(MultipartFile file) {
        boolean fileUploaded = false;
        String filePath = UPLOAD_DIR + File.separator + file.getOriginalFilename();
        try {
            File filePathCheck = new File(filePath);
            if(!filePathCheck.exists()) {
                filePathCheck.mkdir();
            }

            Files.copy(
                    file.getInputStream(),
                    Paths.get(filePath)
            );
            fileUploaded = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return fileUploaded;
    }
}
