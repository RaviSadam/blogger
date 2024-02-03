package com.springboot.blog.Repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.blog.Models.User;
import com.springboot.blog.Projections.ImagesProjection;
import com.springboot.blog.Projections.UserAuthProjection;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u.password AS password, GROUP_CONCAT(r.rolename) AS rolename FROM User u JOIN u.roles r WHERE u.username=:username GROUP BY u.username")
    public UserAuthProjection getUserForAuth(@Param("username")String username);

    //return count of users with username or email
    public long countByUsernameOrEmail(String username,String email);

    @Query("SELECT i.originalName AS originalName, i.imageId AS imageId FROM  User u JOIN u.images i WHERE u.username=:username")
    public Page<ImagesProjection> getImageNameAndId(Pageable page);
    
}
