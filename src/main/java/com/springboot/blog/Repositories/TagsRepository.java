package com.springboot.blog.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.blog.Models.Tags;
import com.springboot.blog.Projections.TagsProjection;

public interface TagsRepository extends JpaRepository<Tags,Integer> {

    //native query
    @Query(value="SELECT t.name AS tagName, COUNT(b.blogid) AS tagCount FROM tags t LEFT JOIN blogs b ON t.name=b.tag GROUP BY t.name",nativeQuery = true)
    public List<TagsProjection> getAllTages();
    
}
