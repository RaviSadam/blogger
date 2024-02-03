package com.springboot.blog.Models;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
public class User implements UserDetails{

    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long userId;


    @Column(name = "username", length = 50,nullable = false,unique = true)
    private String username;

    @Column(name = "firstname", length = 50, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 50)
    private String lastName;

    @Column(unique = true, nullable = false, length = 225)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(length = 10, name = "gender", nullable = false)
    private String gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createddate")
    private Date createdDate;

    @ElementCollection
    private Set<Images> images;

    //user blogs
    @OneToMany(mappedBy = "auther",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Blog> autherBlogs;

    @OneToMany(mappedBy = "reviwer",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Blog> blogsReviwed;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Comments> comments;

    @ManyToMany(mappedBy="likedUsers",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Blog> blogsLiked;

    @ManyToMany(
        cascade = CascadeType.MERGE,
        fetch = FetchType.LAZY
    )
    @JoinTable(
        name = "user_roles",
        joinColumns = {
            @JoinColumn(name="username",referencedColumnName = "username")
        },
        inverseJoinColumns = {
            @JoinColumn(name="roleid",referencedColumnName = "roleid")
        }
    )
    private Set<Roles> roles;
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getSimpleGrantedAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities(){
        return authorities;
    }

    @Transient
    Set<SimpleGrantedAuthority> authorities;
}
