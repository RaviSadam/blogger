package com.springboot.blog.Models;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Roles {
    @Id
    @Column(name="roleid")
    private long id;

    @Column(name="rolename",length = 40,nullable =false,unique = true)
    private String rolename;

    @ManyToMany(
        cascade = CascadeType.MERGE,
        fetch = FetchType.LAZY,
        mappedBy="roles"
    )
    private Set<User> users1;
}
