package com.felix.felixapis.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileUploadHelper {
//    @Value("${project.image}")// local
//    @Value(("/app/target/classes/static/"))
    @Value("/home/ec2-user/static/")
    private String UPLOAD_DIR;


    public boolean uploadFile(MultipartFile file, String fileName) {
        boolean fileUploaded = false;
        String filePath = UPLOAD_DIR + File.separator + fileName;
        try {
            File newFile = new File(UPLOAD_DIR);
            if(!newFile.exists()){
                newFile.mkdir();
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
