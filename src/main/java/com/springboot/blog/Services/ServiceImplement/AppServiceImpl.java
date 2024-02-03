package com.springboot.blog.Services.ServiceImplement;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.blog.BlogApplication;
import com.springboot.blog.Dto.HomeDto;
import com.springboot.blog.Dto.UserRegistration;
import com.springboot.blog.Exceptions.UserExistanceException;
import com.springboot.blog.Models.Roles;
import com.springboot.blog.Models.User;
import com.springboot.blog.Projections.HomeProjection;
import com.springboot.blog.Repositories.BlogRepository;
import com.springboot.blog.Repositories.RolesRepository;
import com.springboot.blog.Repositories.UserRepository;
import com.springboot.blog.Services.AppService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
public class AppServiceImpl implements AppService {

    
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired 
    private final UserRepository userRepository;
    @Autowired
    private final RolesRepository rolesRepository;
    @Autowired 
    private final BlogRepository blogRepository;



    @Override
    public void registration(UserRegistration userRegistration) {
        long cnt=userRepository.countByUsernameOrEmail(userRegistration.getUsername(), userRegistration.getEmail());
        if(cnt!=0)
            throw new UserExistanceException("username or email already in use");
        Set<Roles> roles=userRegistration.getRoles().stream().map(rolename->this.getRoleObject(rolename)).collect(Collectors.toSet());   
        User newUser=User.builder().username(userRegistration.getUsername()).firstName(userRegistration.getFirstName()).lastName(userRegistration.getLastName()).gender(userRegistration.getGender()).email(userRegistration.getEmail()).password(passwordEncoder.encode(userRegistration.getPassword())).createdDate(BlogApplication.todaysDate).roles(roles).build();
        if(newUser!=null)
            userRepository.save(newUser);
        
        if(SecurityContextHolder.getContext().getAuthentication()!=null){
            SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        }
        //User object no more use full so making availabel for GC
        log.info("New User registered with username:: "+userRegistration.getUsername());
        newUser=null;
        roles=null;
    }

    @Override 
    @Cacheable(cacheNames = "roles")
    public List<String> getAllRoles(){
        return  rolesRepository.getAllRoles()
                                .stream()
                                .map(role->role.getRolename())
                                .collect(Collectors.toList());
    }


    @Override
    public List<HomeDto> getHomeArticles(int pageNumber,int pageSize){
        
        int skip=pageNumber*pageSize;
        return  blogRepository.getHomeArticles(skip).stream()
                            .map(projection-> HomeDto.builder().content(projection.getContent()).id(projection.getId()).title(projection.getTitle()).username(projection.getUsername()).build())
                            .collect(Collectors.toList());

    }

    @Override
    public HomeDto getBlogData(String id,String username){
        HomeProjection pro=blogRepository.getBlogData(id);
        return HomeDto.builder().content(pro.getContent()).id(pro.getId()).isLiked(false).title(pro.getTitle()).content(pro.getContent()).username(pro.getUsername()).likes(pro.getLikes()).build();
    }



    private Roles getRoleObject(String role){
        if(role.equals("ROLE_ADMIN"))
            return Roles.builder().id(1).rolename(role).build();
        return Roles.builder().id(2).rolename(role).build();
    }
    public String getUuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    
}
