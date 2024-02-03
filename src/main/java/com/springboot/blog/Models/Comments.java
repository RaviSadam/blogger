package com.springboot.blog.Models;

import java.util.Date;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Entity
@Table(name="comments")
@DynamicInsert
@DynamicUpdate
@Builder
public class Comments {
    @Id
    
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long id;

    @Column(length = 200)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(
        name = "username",
        referencedColumnName = "username"
    )
    private User user;


    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    )
    @JoinColumn(
        name="blogid",
        referencedColumnName = "blogid"
    )
    private Blog blog;
}
