package com.springboot.blog.Services;


import java.util.List;


import com.springboot.blog.Dto.HomeDto;
import com.springboot.blog.Dto.UserRegistration;

public interface AppService {

    //registring new user
    public void registration(UserRegistration userRegistration);

    //available roles
    public List<String> getAllRoles();
    
    public List<HomeDto> getHomeArticles(int pageNumber,int pageSize);

    public HomeDto getBlogData(String id,String username);

}
