package com.springboot.blog.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.blog.Dto.AddNewTitleDto;
import com.springboot.blog.Dto.BlogData;
import com.springboot.blog.Dto.ImagesDto;
import com.springboot.blog.Dto.TagsDto;
import com.springboot.blog.Projections.BlogTitleProjection;
import com.springboot.blog.Projections.MyArticlesProjection;



public interface BlogService {

    //add new categeory to DB
    public boolean addCategory(String categeory);

    //returns all available tags
    public List<String> getAllTages();

    //adding new title to Blogs table
    public boolean addNewTitle(AddNewTitleDto addNewTitleDto);

    //returns the 
    public List<TagsDto> getAllTagsWithCnt();

    //retuns all blogs with PICK status
    public Page<BlogTitleProjection> getBlogTitles(String category,int pageNumber);

    //returns count of blog titles
    public long getBlogTitleCount();

    //mark the article picked by user
    public boolean pickArticle(String blogid,String username);

    //make the blog for review
    public boolean reviewBlog(BlogData blogData) throws IOException;

    //returns user articles which are in draf,review,pending review
    public Page<MyArticlesProjection> getMyArticles(String username,int pageNumber);


    //discard the articles
    public void discardArticle(String blogId);

    //gives user images names corresponding link
    public List<ImagesDto> getImagesLinks(String username);

}
