package com.springboot.blog.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class TagsDto {
    public String name;
    public int count;
} 
