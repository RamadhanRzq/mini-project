package com.miniproject.pos.posprodemy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_Id")
    private Long id;

    private String name;
}
