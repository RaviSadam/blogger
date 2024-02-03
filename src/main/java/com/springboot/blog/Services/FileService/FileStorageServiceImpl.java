package com.springboot.blog.Services.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private Path directory;

    @Override
    @PostConstruct
    public void init() {
        this.directory=Paths.get("/src/main/resources/static/images");    
    }

    @Override
    public String save(MultipartFile multipartFile) {
        return null;
    }

    @Override
    public void delete(String fileName) {
        try {
            Files.delete(this.directory.resolve(fileName));
        } catch (IOException e) {
            log.error(e);
        }
    }

    @Override
    public Resource getImage(String fileName) {
        return null;
    }    
}
