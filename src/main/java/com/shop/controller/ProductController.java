package com.shop.controller;

import com.shop.dto.ProductDto;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.service.ProductService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @GetMapping("/product/{id}")
    public String showProductPage(@PathVariable("id") long id, Model model) {
        Product product = productService.findById(id);
        if(product == null)
            return "redirect:/";

        model.addAttribute("product", product);
        return "product";
    }

    @GetMapping("/admin/addProduct")
    public String showAddProductPage(Model model) {
        model.addAttribute("product", new ProductDto());
        return "add-product";
    }

    @PostMapping("/admin/addProduct")
    public String sendProductToDatabase(@Valid @ModelAttribute("product") ProductDto productDto, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/error";
        }
        Product product = productService.save(productDto);
        return "redirect:/";
    }

    @PostMapping("/product/follow/{id}")
    public String followProduct(@PathVariable("id") long id, Principal principal){
        User user = userService.findByUsername(principal.getName());
        Product product = productService.findById(id);
        userService.addProductSubscriptionToUser(product, user);

        return "redirect:/product/" + id;
    }
    @GetMapping("/followed")
    public String showFollowed(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<Product> products = productService.findFollowedProductsByUser(user);
        model.addAttribute("products", products);
        return "followed";
    }
    @PostMapping("/product/follow/delete/{id}")
    public String deleteFollowing(@PathVariable("id") long id, Principal principal){
        User user = userService.findByUsername(principal.getName());
        Product product = productService.findById(id);
        userService.deleteProductSubscriptionFromUser(product, user);

        return "redirect:/followed";
    }

}
