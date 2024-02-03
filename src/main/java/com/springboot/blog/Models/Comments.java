package com.springboot.blog.Models;

import java.util.Date;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
@Builder
@Getter
@Setter
@ToString
public class Comments {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    @Column(unique = true)
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
