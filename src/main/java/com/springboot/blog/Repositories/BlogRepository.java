package com.springboot.blog.Repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.blog.Models.Blog;
import com.springboot.blog.Projections.BlogTitleProjection;
import com.springboot.blog.Projections.CountProjection;
import com.springboot.blog.Projections.HomeProjection;
import com.springboot.blog.Projections.MyArticlesProjection;

import jakarta.transaction.Transactional;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    @Query(value="SELECT b.id AS id,b.title AS title,b.tag AS category,b.explanation AS explanation FROM Blog b WHERE b.status='PICK'")
    public Page<BlogTitleProjection> getBlogTitleWithoutTage(Pageable page);

    @Query(value="SELECT b.id AS id,b.title AS title,b.tag AS category,b.explanation AS explanation FROM Blog b WHERE b.tag=:tag AND b.status='PICK'")
    public Page<BlogTitleProjection> getBlogTitleWithTage(@Param("tag") String category,Pageable page);


    @Query(value="SELECT COUNT(*) AS count FROM Blog b WHERE b.status='PICK' GROUP BY b.tag")
    public CountProjection countBlogTitles();

    @Transactional
    @Modifying
    @Query(value="UPDATE blogs b SET b.status='DRAFT',b.picked_date=:pickedDate, b.auther=:auther WHERE b.blogid=:blogId",nativeQuery = true)
    public void updateStatusAndDate(@Param("blogId")String blogId,@Param("pickedDate") Date date,@Param("auther") String auther);

    @Transactional
    @Modifying
    @Query(value="UPDATE blogs b SET b.status='POSTED',b.content=:content WHERE b.blogid=:blogId",nativeQuery = true)
    public void updateBlogContentAndStatus(@Param("blogId")String blogId,@Param("content") String content);

    @Query("SELECT b.title AS title,b.id AS id,b.pickedDate AS pickedDate,b.status AS status FROM Blog b JOIN b.auther a WHERE a.username=:username AND b.status IN ('DRAFT','PENDING_REVIEW','AUTHER_WAITING')")
    public Page<MyArticlesProjection> getUserPickedArticles(String username, Pageable page);

    @Modifying
    @Transactional
    @Query("UPDATE Blog b SET b.content=:content WHERE b.id=:blogId")
    public void saveBlog(@Param("content") String content,@Param("blogId") String blogId);

    @Modifying
    @Transactional
    @Query(value="UPDATE blogs b SET b.auther=NULL, b.content=NULL, b.status='PICK' WHERE b.blogid=:id",nativeQuery = true)
    public void discardArticle(@Param("id") String id);

    @Query(nativeQuery = true,value = "SELECT b.title AS title, LEFT(b.content,20) AS content, b.auther AS username, b.blogid as id FROM blogs b WHERE  b.status='POSTED' ORDER BY RAND() LIMIT :skip,10")
    public List<HomeProjection> getHomeArticles(@Param("skip") int skip);

    @Modifying
    @Transactional
    @Query(value="UPDATE blogs  SET likes=likes+1 WHERE blofid=:blogid",nativeQuery = true)
    public void addLike(@Param("blogid")String blogid);


    @Query(nativeQuery = true,value = "SELECT b.title AS title, b.content AS content, b.auther AS username, b.blogid as id, b.likes AS likes, b.likes!=1 AS isLiked FROM blogs b WHERE b.blogid=:id")
    public HomeProjection getBlogData(@Param("id") String id);


 
    @Modifying
    @Transactional
    @Query(value="UPDATE blogs SET content=NULL,auther=NULL,status='PICK',picked_date=NULL WHERE picked_date IS NOT NULL AND DATEDIFF('DAY',CURRENT_DATE(),picked_date)>5;",nativeQuery = true)
    public void updateStatus();
}
