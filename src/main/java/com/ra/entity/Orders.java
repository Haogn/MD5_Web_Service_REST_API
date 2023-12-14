package com.ra.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id ;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
    private User user ;

    @Column(name = "address_ship", nullable = false)
    private String addressShip ;

    @Column(name = "phone" , nullable = false)
    private String phone ;

    @Column(name = "note")
    private String note ;

    @Column(name = "date_order")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOrder ;

    @Column(name = "status")
    private boolean status = false ;

}
