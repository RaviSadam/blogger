package com.springboot.blog.Projections;

public interface HomeProjection {
    String getTitle();
    String getContent();
    String getId();
    String getUsername();
    int getLikes();
}
