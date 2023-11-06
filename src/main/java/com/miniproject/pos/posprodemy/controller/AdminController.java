package com.miniproject.pos.posprodemy.controller;

import com.miniproject.pos.posprodemy.dto.ProductDto;
import com.miniproject.pos.posprodemy.model.*;
import com.miniproject.pos.posprodemy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    ShippingService shippingService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    UserService userService;

    //HOME
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    //CATEGORY
    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }
    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable Long id){
        categoryService.removeCategoryById(id);
        return "/redirect:/admin/categories";
    }
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

    // PRODUCT
    @GetMapping("/admin/products")
    public String getProducts(Model model){
        model.addAttribute("products",productService.getAllProduct());
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String productsAddGet(Model model){
        model.addAttribute("productDTO", new ProductDto());
        model.addAttribute("categories",categoryService.getAllCategory());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO") ProductDto productDto,
                                 @RequestParam("productImage") MultipartFile file,
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
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        Product product = productService.getProductById(id).get();
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setProductName(product.getProductName());
        productDto.setCategoryId((product.getCategory().getId()));
        productDto.setProductPrice(product.getProductPrice());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setImageName(product.getImageName());

        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO",productDto);
        return "productsAdd";
    }
    @GetMapping("/admin/product/detail/{id}")
    public String detailProduct(Model model, @PathVariable Long id){
        model.addAttribute("product",productService.getProductById(id).get());
        return "detailProduct";
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

    //USER
    @GetMapping("/admin/user")
    public String getUser(Model model){
        model.addAttribute("users",userService.getAllUser());
        return "user";
    }
    @GetMapping("/admin/user/add")
    public String getUserAdd(Model model){
        model.addAttribute("user", new User());
        return "userAdd";
    }
    @PostMapping("/admin/user/add")
    public String postUserAdd(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/admin/user";
    }
    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.removeUserById(id);
        return "redirect:/admin/user";
    }
    @GetMapping("/admin/user/update/{id}")
    public String updateUser(@PathVariable Long id, Model model){
        Optional<User> user = userService.getUserById(id);

        if(user.isPresent()) {
            model.addAttribute("user", user.get());
            return "userAdd";
        } else {
            return "404";
        }
    }

    //PAYMENT
    @GetMapping("/admin/payment")
    public String getPayment(Model model){
        model.addAttribute("payments",paymentService.getAllPayment());
        return "payment";
    }
    @GetMapping("/admin/payment/add")
    public String getPaymentAdd(Model model){
        model.addAttribute("payment", new Payment());
        return "paymentAdd";
    }
    @PostMapping("/admin/payment/add")
    public String postPaymentAdd(@ModelAttribute("payment") Payment payment){
        paymentService.addPayment(payment);
        return "redirect:/admin/payment";
    }
    @GetMapping("/admin/payment/delete/{id}")
    public String deletePayment(@PathVariable Long id){
        paymentService.removePaymentById(id);
        return "redirect:/admin/payment";
    }
    @GetMapping("/admin/payment/update/{id}")
    public String updatePayment(@PathVariable Long id, Model model){
        Optional<Payment> payment = paymentService.getPaymentById(id);

        if(payment.isPresent()) {
            model.addAttribute("payment", payment.get());
            return "paymentAdd";
        } else {
            return "404";
        }
    }
}
