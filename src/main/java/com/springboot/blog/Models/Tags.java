package com.springboot.blog.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tags")
@Builder
public class Tags {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    @Column(name="tagid",length=50)
    private int tagId;

    @Column(name="name",unique = true,nullable = false,length=50,updatable=false)
    private String tagName;
}
