package com.shop.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin/adminPage")
    public String showAdminPage(){
        return "adminPage";
    }

    @PostMapping("/admin/changeBanStatus")
    public String changeBanStatus(@RequestParam("userToChange") String username)
    {
        if(userService.findByUsername(username) != null)
        {
            User user = userService.findByUsername(username);
            userService.changeBanStatus(user);
        }
        else
            return "redirect:/admin/adminPage?err";

        return "redirect:/admin/adminPage";
    }

    @PostMapping("/admin/removeProduct")
    public String removeProduct(@RequestParam("productToRemoveID") long id)
    {
        if(productService.findById(id) != null)
        {
            Product product = productService.findById(id);
            productService.deleteProduct(product);
        }
        else
            return "redirect:/admin/adminPage?err";

       return "redirect:/admin/adminPage";
    }

    @PostMapping("/admin/changeProductAmount")
    public String changeProductAmount(@RequestParam("productToChangeID") long id, @RequestParam("amountToChange") int amount)
    {
        if(productService.findById(id) != null )
        {
            if(productService.findById(id).getAmount() + amount >= 0)
            {
                Product product = productService.findById(id);
                product.setAmount(product.getAmount() + amount);
                productService.save(product);
            }
            else
                return "redirect:/admin/adminPage?err";
        }
        else
            return "redirect:/admin/adminPage?err";

        return "redirect:/admin/adminPage";
    }

}
