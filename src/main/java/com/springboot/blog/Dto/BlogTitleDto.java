package com.springboot.blog.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogTitleDto {
    private String id;
    private String title;
    private String category;
    private String explanation;
}
