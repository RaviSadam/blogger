package com.springboot.blog.Projections;

// b.id AS id,b.title AS title,b.tag AS category,b.explanation AS explanation
public interface BlogTitleProjection {
    public String getExplanation();
    public String getId();
    public String getCategory();
    public String getTitle();
}
