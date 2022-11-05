package com.felix.felixapis.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MediaStorageService {
    private final Path root = Paths.get("media");

    public void createDir() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Can't create directory");
        }
    }

    public void upload(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Can't store the file");
        }
    }

    public Resource download(String fileName) {
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Can't read the file");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
