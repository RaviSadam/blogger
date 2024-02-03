package com.springboot.blog.Projections;

import java.util.Date;

public interface MyArticlesProjection {
    public String getTitle();
    public String getId();
    public Date getPickedDate();
    public String getStatus();
}
