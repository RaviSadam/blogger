package com.springboot.blog.Projections;

import java.util.Date;

public interface CommentsProjection {
    public String getComment();
    public String getUsername();
    public String getBlogId();
    public Date getDate();
}
