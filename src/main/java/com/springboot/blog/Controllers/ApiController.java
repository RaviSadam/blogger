package com.springboot.blog.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springboot.blog.Dto.BlogData;
import com.springboot.blog.Dto.CommentDto;
import com.springboot.blog.Models.User;
import com.springboot.blog.Services.ApiService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {
    
    private final ApiService apiService;

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveBlog(@ModelAttribute BlogData blogData) throws IOException{
        try{
            return apiService.saveBlog(blogData);
        }
        catch(IOException exp){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }
    @PostMapping("/comments")
    public ResponseEntity<?> addComment(@ModelAttribute CommentDto commentDto,@AuthenticationPrincipal UserDetails userDetails) throws IOException{
        apiService.addComment(commentDto, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/comments/{blogid}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("blogid")String blogid){
        return ResponseEntity.ok().body(apiService.getComments(blogid));
    }
    

    @GetMapping("/like/{id}")
    public ResponseEntity<?> addLike(@PathVariable("id")String id,@AuthenticationPrincipal User  user ){
        apiService.addLike(user.getUsername(), id);
        return ResponseEntity.ok().build();
    }

}
