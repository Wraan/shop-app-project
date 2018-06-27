package com.shop.controller;

import com.shop.model.Address;
import com.shop.model.Cart;
import com.shop.model.Product;
import com.shop.model.User;
import com.shop.service.AddressService;
import com.shop.service.CartService;
import com.shop.service.ProductService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    AddressService addressService;

    @GetMapping("/addCartToSession")
    public String addCartToSession(HttpSession session, Principal principal){
        User user = userService.findByUsername(principal.getName());
        Cart cart = cartService.findNotBoughtByUser(user);
        if(cart == null){
            cart = cartService.save(new Cart(user));
        }
        session.setAttribute("cart", cart);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String showCurrentCart(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        Cart cart = cartService.findNotBoughtByUser(user);
        model.addAttribute("products", cartService.getProductsFromCart(cart));
        model.addAttribute("buyBtn", true);
        return "cart";
    }

    @GetMapping("/cart/{id}")
    public String showSpecificCart(@PathVariable("id") long id, Model model){
        Cart cart = cartService.findById(id);
        if(cart == null)
            return "redirect:/error";
        model.addAttribute("products", cartService.getProductsFromCart(cart));
        model.addAttribute("buyBtn", false);
        return "cart";
    }

    @PostMapping("/cart/add/{id}")
    public String addProductToCart(@PathVariable("id") long id, HttpSession session){
        Product product = productService.findById(id);
        Cart cart = cartService.addProductToCart(product, (Cart) session.getAttribute("cart"));
        session.setAttribute("cart", cart);
        return "redirect:/product/" + id;
    }
    @PostMapping("/cart/delete/{id}")
    public String deleteProductFromCart(@PathVariable("id") long id, HttpSession session){
        Product product = productService.findById(id);
        Cart cart = cartService.deleteProductFromCart(product, (Cart) session.getAttribute("cart"));
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart/confirmAddress")
    public String showAddresses(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        List<Address> addresses = user.getAddresses();

        model.addAttribute("addresses", addresses);
        return "confirm-address";
    }

    @PostMapping("/cart/confirmAddress/{id}")
    public String confirmAddress(@PathVariable("id") long id, Model model, HttpSession session){
        Address address = addressService.getByID(id);

        session.setAttribute("address", address);
        return "redirect:/order";
    }
    @GetMapping("/order")
    public String showOrder(Model model, HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");

        Address address = (Address) session.getAttribute("address");
        model.addAttribute("address", address);

        double price = cartService.getCartPrice(cart);
        model.addAttribute("price", price);

        session.setAttribute("address", address);
        return "order";
    }

    @GetMapping("/order/confirm")
    public String confirmOrder(HttpSession session, Principal principal){
        Cart cart = (Cart) session.getAttribute("cart");
        Address address = (Address) session.getAttribute("address");

        cart = productService.buyProductsInCart(cart, address);
        if(cart == null) {
            session.removeAttribute("address");
            return "redirect:/error";
        }
        cart = new Cart(userService.findByUsername(principal.getName()));
        cartService.save(cart);

        session.setAttribute("cart", cart);
        session.removeAttribute("address");

        return "order-confirm";
    }
    @GetMapping("/carts/bought")
    public String showBoughtCarts(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<Cart> carts = cartService.findBoughtByUser(user);

        model.addAttribute("carts", carts);
        return "carts-bought";
    }
    @GetMapping("/pop")
    public String popular(){
        cartService.findMostPopularProducts();
        return "redirect:/";
    }
}
