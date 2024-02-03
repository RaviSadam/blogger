package com.springboot.blog.Models;

import java.util.Date;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="blogs")
@DynamicInsert
@DynamicUpdate
public class Blog {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long generatedId;


    @Column(name = "blogid",length = 50,nullable = false,unique = true)
    private String id;

    @Column(name="title",length = 100)
    private String title;

    @Column(length = 250)
    private String explanation;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('PICK','DRAFT','PENDING_REVIEW','AUTHER_WAITING','POSTED') DEFAULT 'PICK'")
    private Status status;

    @Column(length=50,nullable = false,unique = false)
    private String tag;

    @Lob()
    @Column(columnDefinition = "TEXT DEFAULT NULL")
    private String content;

    @Column(columnDefinition = "INT DEFAULT 0")
    private long likes;
    
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Temporal(TemporalType.DATE)
    private Date pickedDate;

    //user blogs
    @ManyToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "auther",
        referencedColumnName = "username"
    )
    private User auther;


    //reviwer
    @ManyToOne(
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JoinColumn(
        name = "reviwer",
        referencedColumnName = "username"
    )
    private User reviwer;


    //liked users
    @ManyToMany(
        cascade=CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JoinTable(
        name="blog_likes",
        joinColumns = {
            @JoinColumn(
                name="blogid",
                referencedColumnName = "blogid"
            )
        },
        inverseJoinColumns = {
            @JoinColumn(
                name="username",
                referencedColumnName = "username"
            )
        }
    )
    private Set<User> likedUsers;
}
