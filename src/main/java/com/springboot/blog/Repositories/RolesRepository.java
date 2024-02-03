package com.springboot.blog.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.blog.Models.Roles;
import com.springboot.blog.Projections.RolesProjection;

public interface RolesRepository extends JpaRepository<Roles,Long>{
    public Optional<Roles> getReferenceByRolename(String name); 

    @Query("SELECT r.rolename as rolename FROM Roles r")
    public List<RolesProjection> getAllRoles();
    
}
