package com.springboot.blog.Services.ServiceImplement;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.springboot.blog.Dto.BlogData;
import com.springboot.blog.Dto.CommentDto;
import com.springboot.blog.Repositories.BlogRepository;
import com.springboot.blog.Services.ApiService;
import com.springboot.blog.Services.CompressionDecompression.CompressDecompress;


@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private  BlogRepository blogRepository;
    
    // @Autowired
    // @Lazy
    // private  CommentsRespository commentsRespository;


    @Override
    public void addLike(String username,String id){
        blogRepository.addLike(id);
    }

    @Override
    public ResponseEntity<?> saveBlog(BlogData blogData) throws IOException {
        if(blogData.getBlogId()==null || blogData.getContent()==null)
            throw new DataIntegrityViolationException("Requried fields misssing");
        blogRepository.saveBlog(CompressDecompress.compress(blogData.getContent()),blogData.getBlogId());
       return ResponseEntity.ok().build();
    }

    @Override
    public boolean addComment(CommentDto commentDto,String username){
        // commentsRespository.addComment(commentDto.getComment(), BlogApplication.todaysDate,commentDto.getBlogId(),username);
        return true;
    }

    @Override
    public List<CommentDto> getComments(String blogId){
        // commentsRespository.getComments(blogId);
        return null;
    }
    
}
