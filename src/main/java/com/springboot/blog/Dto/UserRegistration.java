package com.springboot.blog.Dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistration {
    @Size(min = 5,max = 10,message = "username contains 5 to 10 characters only")
    @NotBlank(message = "Blank username not allowed")
    @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9]*",message = "invalid username")
    private String username;

    @NotBlank(message = "First name of user not blank")
    @Size(min=3,max = 50,message ="Firstname contains 5 to 50 characters only")
    @Pattern(regexp = "[a-zA-Z]+",message = "First name should contains Alphabets only")
    private String firstName;
    
    @Pattern(regexp = "[a-zA-Z]+",message = "Last name should contains Alphabets only")
    private String lastName;

    @NotBlank(message = "Blank password not allowed")
    @Size(min=8,max = 20,message ="Password length between 8 to 20 characters only")
    private String password;

    @Pattern(regexp = "[A-Z]{4,6}$",message = "Invalid gender is given")
    private String gender;
    
    @Email(message = "Invalid Email is given")
    private String email;

    @NotNull(message = "Role Must not be NULL")
    @Size(min=1,message = "Roles requried")
    private List<String> roles;
}
