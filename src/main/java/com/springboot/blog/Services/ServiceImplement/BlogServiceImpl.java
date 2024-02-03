package com.springboot.blog.Services.ServiceImplement;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.blog.BlogApplication;
import com.springboot.blog.Dto.AddNewTitleDto;
import com.springboot.blog.Dto.BlogData;
import com.springboot.blog.Dto.ImagesDto;
import com.springboot.blog.Dto.TagsDto;
import com.springboot.blog.Models.Blog;
import com.springboot.blog.Models.Status;
import com.springboot.blog.Models.Tags;
import com.springboot.blog.Projections.BlogTitleProjection;
import com.springboot.blog.Projections.CountProjection;
import com.springboot.blog.Projections.MyArticlesProjection;
import com.springboot.blog.Projections.TagsProjection;
import com.springboot.blog.Repositories.BlogRepository;
import com.springboot.blog.Repositories.TagsRepository;
import com.springboot.blog.Services.BlogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service
@RequiredArgsConstructor
@Log4j2
public class BlogServiceImpl implements BlogService {


    private final TagsRepository tagsRepository;
    private final BlogRepository blogRepository;


    private Date getDate(){
        return new Date(System.currentTimeMillis());
    }

    //addes a new category to DB 
    @Override
    public boolean addCategory(String categeory) {
        Tags tag=Tags.builder().tagName(categeory).build();
        try{
            if(tag!=null){
                tagsRepository.save(tag);    
                log.info("New category added with name::"+categeory+" At"+this.getDate());
            }
        }
        catch(DataIntegrityViolationException ex){
            String message=ex.getMessage();
            if(message!=null)
                throw new DataIntegrityViolationException(message);
        }
        return true;
    }

    //returns all availabel tages(categories) names
    @Override
    @Cacheable(cacheNames = "tags")
    public List<String> getAllTages(){
        return this.getTagsProjection().stream().map(tag->tag.getTagName()).collect(Collectors.toList());
    }

    //adding new title to DB
    @Override
    public boolean addNewTitle(AddNewTitleDto addNewTitleDto) {
        try{
            Blog blog=Blog.builder()
                        .id(this.getBlogUUID())
                        .title(addNewTitleDto.getTitle())
                        .createdDate(BlogApplication.todaysDate)
                        .explanation(addNewTitleDto.getExplanation())
                        .status(Status.PICK)
                        .tag(addNewTitleDto.getTag())
                        .build();
            if(blog!=null){
                blogRepository.save(blog);
                log.info("New Title is added with id "+blog.getId()+" At"+this.getDate());
            }
        }
        catch(DataIntegrityViolationException ex ){
            String message=ex.getMessage();
            if(message!=null)
                throw new DataIntegrityViolationException(message);
        }
        return true;
    }


    //retrun tags with count
    @Override
    @Cacheable(cacheNames = "tags")
    public List<TagsDto> getAllTagsWithCnt() {
        return this.getTagsProjection().stream().map(tags->TagsDto.builder().name(tags.getTagName()).count(tags.getTagCount()).build()).collect(Collectors.toList());
    }


    // return BlogTitleDto with title,id,categeory,explanation
    @Override
    // @Cacheable(cacheNames = "blogs",key="#category+'-'+#pageNumber")
    public Page<BlogTitleProjection> getBlogTitles(String category,int pageNumber){
        Pageable page=PageRequest.of(Math.min(0,pageNumber-1),10);
        if(category.equals("random")){
            return blogRepository.getBlogTitleWithoutTage(page);
        }
        else{
            return blogRepository.getBlogTitleWithTage(category, page);
        }
    }

    //returns the count of articles avaliable for pick
    @Override
    public long getBlogTitleCount(){
        CountProjection countProjection=blogRepository.countBlogTitles();
        return countProjection.getCount();
    }

    @Override
    public boolean pickArticle(String blogId,String username){
        try{
            blogRepository.updateStatusAndDate(blogId,BlogApplication.todaysDate,username);
            return true;
        }
        catch(Exception ex){
            log.info(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean reviewBlog(BlogData blogData) throws IOException {
        try {
            blogRepository.updateBlogContentAndStatus(blogData.getBlogId(),blogData.getContent());
        } catch (Exception e) {
           throw new IOException(e.getMessage(),e.getCause());
        }
        return true;
    }

    @Override
    public Page<MyArticlesProjection> getMyArticles(String username,int pageNumber){
        Pageable page=PageRequest.of(pageNumber-1, 15);
        return blogRepository.getUserPickedArticles(username,page);
    }

    @Override
    public void discardArticle(String blogId){
        blogRepository.discardArticle(blogId);
    }

    @Override
    public List<ImagesDto> getImagesLinks(String username){
        return null;
    }
    

    //utility methods
    private String getBlogUUID(){
        return "Blog-"+(UUID.randomUUID().toString().replace("-", ""));
    }

    //this will give the tags with count
    private List<TagsProjection> getTagsProjection(){
        return tagsRepository.getAllTages();
    }

}
