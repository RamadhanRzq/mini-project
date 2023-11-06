package com.miniproject.pos.posprodemy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_Id")
    private Long id;

    private String name;
}