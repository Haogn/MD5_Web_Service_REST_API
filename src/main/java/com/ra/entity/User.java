package com.ra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id ;
    @Column(name = "full_name")
    private String fullName ;
    @Column(name = "email" , unique = true)
    private String email ;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Orders> orders ;

}
