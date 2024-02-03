package com.springboot.blog.Dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MyArticlesDto {
    private String title;
    private String id;
    private Date dueDate;
    private String status;
}
