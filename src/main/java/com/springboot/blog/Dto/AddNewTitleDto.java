package com.springboot.blog.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddNewTitleDto {

    @NotBlank(message = "Blank title not allowed")
    @Size(min=4,max=50,message="Title length between 10 to 10 only")
    private String title;

    @NotBlank(message = "Blank Explanation not allowed")
    @Size(min=30,max=250,message = "explanation should be 30 to 250 characters")
    private String explanation;

    @NotBlank(message = "Blank tags not allowed")
    private String tag;    
}
