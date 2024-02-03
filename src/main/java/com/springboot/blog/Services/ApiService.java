package com.springboot.blog.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.blog.Dto.BlogData;
import com.springboot.blog.Dto.CommentDto;

import java.io.IOException;
import java.util.List;

public interface ApiService {

    //saves article and return response entity
    public ResponseEntity<?> saveBlog(BlogData blogData) throws IOException;

    //uploades images
    public default ResponseEntity<?> uploadImage(MultipartFile multipartFile){
        return null;
    }

    public boolean addComment(CommentDto commentDto,String username);
    public List<CommentDto> getComments(String blogId);

    public void addLike(String username,String id);
}
