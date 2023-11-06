package com.prodemy.miniproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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