package com.springboot.blog.Services.FileService;

import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    //initializong the file service
    public void init();

    //saves the images
    public String save(MultipartFile multipartFile);
    
    //delete image
    public void delete(String fileName);
    
    //give image
    public Resource getImage(String fileName);

    default String getUniqueName(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
