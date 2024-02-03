package com.springboot.blog.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomeDto {
    String title;
    String content;
    String id;
    String username;
    int likes;
    boolean isLiked;
}
