package com.miniproject.pos.posprodemy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", referencedColumnName = "category_id")
    private Category category;

    private double productPrice;

    private String productDescription;

    private String imageName;
}
