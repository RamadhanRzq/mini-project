package com.prodemy.miniproject.controller;

import com.prodemy.miniproject.global.GlobalData;
import com.prodemy.miniproject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.prodemy.miniproject.service.CategoryServiceImpl;
import com.prodemy.miniproject.service.ProductServiceImpl;

import java.util.List;

@Controller
public class HomeController {
	
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    ProductServiceImpl productService;

    @GetMapping({"/","/home"})
    public String home(Model model){
        return "shop";
    }
    
    @GetMapping("/shop")
    public String shop(Model model, @Param("keyword") String keyword){
        List<Product> listProduct = productService.searchProductByName(keyword);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("keyword",keyword);
        return "shop";
    }
    
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable Long id){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("products", productService.getAllProductsById(id));
        return "shop";
    }
    
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable Long id){
        model.addAttribute("product",productService.getProductById(id).get());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "viewProduct";
    }

}
