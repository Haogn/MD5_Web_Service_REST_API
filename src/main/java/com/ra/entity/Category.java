package com.ra.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "category_name", unique = true)
    private String categoryName ;

    @Column(name = "category_status")
    private Boolean categoryStatus ;
    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY)
    @Transient
    private List<Product> products ;


}
