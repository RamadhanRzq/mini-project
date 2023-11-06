package com.prodemy.miniproject.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.prodemy.miniproject.model.Shipping;
import com.prodemy.miniproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.prodemy.miniproject.dto.ProductDto;
import com.prodemy.miniproject.model.Category;
import com.prodemy.miniproject.model.Product;

@Controller
public class AdminController {
	
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    ShippingServiceImpl shippingService;
    
    // Show admin home
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }
    
    // Show admin categories
    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        return "categories";
    }
    
    // Show admin add new categories
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }
    
    // Add new categories
    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    
    // Remove categories
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable Long id){
        categoryService.removeCategoryById(id);
        return "/redirect:/admin/categories";
    }
    
    // Edit/update categories
    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable Long id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()) {
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else {
            return "404";
        }
    }

    //Show admin all products
    @GetMapping("/admin/products")
    public String getProducts(Model model){
        model.addAttribute("products",productService.getAllProducts());
        return "products";
    }
    
    // Show admin add products
    @GetMapping("/admin/products/add")
    public String productsAddGet(Model model){
        model.addAttribute("productDTO", new ProductDto());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "productsAdd";
    }
    
    // Add new products
    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDto") ProductDto productDto,
                                 @RequestParam("productImage")MultipartFile file,
                                 @RequestParam("imgName")String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setProductName(productDto.getProductName());
        product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()).get());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductDescription(productDto.getProductDescription());
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        } else {
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }
    
    // Delete products
    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }
    
    // Edit/update products
    @GetMapping("/admin/products/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        Product product = productService.getProductById(id).get();
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setCategoryId((product.getCategory().getId()));
        productDto.setProductPrice(product.getProductPrice());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setImageName(product.getImageName());

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("productDto",productDto);
        return "productsAdd";
    }


    //SHIPPING
    @GetMapping("/admin/shipping")
    public String getShipping(Model model){
        model.addAttribute("shippings",shippingService.getAllShipping());
        return "shipping";
    }
    @GetMapping("/admin/shipping/add")
    public String getShippingAdd(Model model){
        model.addAttribute("shipping", new Shipping());
        return "shippingAdd";
    }
    @PostMapping("/admin/shipping/add")
    public String postShippingAdd(@ModelAttribute("shipping") Shipping shipping){
        shippingService.addShipping(shipping);
        return "redirect:/admin/shipping";
    }
    @GetMapping("/admin/shipping/delete/{id}")
    public String deleteShipping(@PathVariable Long id){
        shippingService.removeShippingById(id);
        return "/redirect:/admin/shipping";
    }
    @GetMapping("/admin/shipping/update/{id}")
    public String updateShipping(@PathVariable Long id, Model model){
        Optional<Shipping> shipping = shippingService.getShippingById(id);

        if(shipping.isPresent()) {
            model.addAttribute("shipping", shipping.get());
            return "shippingAdd";
        } else {
            return "404";
        }
    }
    
}
