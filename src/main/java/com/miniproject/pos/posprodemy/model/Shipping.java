package com.miniproject.pos.posprodemy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shipping_Id")
    private Long id;

    private String name;
}