package com.springboot.blog.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonDeserialize
@ToString
public class CommentDto{
    
    @JsonProperty("comment")
    private String comment;

    @JsonProperty("blogId")
    private String blogId;
    
    @JsonIgnoreProperties
    private Data date;
    
    @JsonIgnoreProperties
    private String username;
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }
    public void setDate(Data date) {
        this.date = date;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getComment() {
        return comment;
    }
    public String getBlogId() {
        return blogId;
    }
    public Data getDate() {
        return date;
    }
    public String getUsername() {
        return username;
    }
    
    
}
