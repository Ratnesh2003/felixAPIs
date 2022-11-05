package com.felix.felixapis;

import com.felix.felixapis.services.MediaStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class FelixApIsApplication implements CommandLineRunner {
    @Resource
    MediaStorageService mediaStorageService;

    public static void main(String[] args) {
        SpringApplication.run(FelixApIsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mediaStorageService.createDir();
    }
}
