package com.prodemy.miniproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.prodemy.miniproject.service.CategoryServiceImpl;
import com.prodemy.miniproject.service.ProductServiceImpl;

@Controller
public class HomeController {
	
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    ProductServiceImpl productService;

    @GetMapping({"/","/home"})
    public String home(Model model){
        return "index";
    }
    
    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        return "shop";
    }
    
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable Long id){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProductsById(id));
        return "shop";
    }
    
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable Long id){
        model.addAttribute("product",productService.getProductById(id).get());
        return "viewProduct";
    }
    
}
