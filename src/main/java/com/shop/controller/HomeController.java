package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }


    @RequestMapping("/errors")
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest,
                                        @RequestParam("nr") Integer nr) {

        ModelAndView errorPage = new ModelAndView("403");
        String errorMsg;

        switch (nr) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
            default:{
                errorMsg = "Ups! Something is not ok";
            }
        }
        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;
    }

    @RequestMapping("favicon.ico")
    public static String favicon() {
        return "forward:../favicon.ico";
    }

    @GetMapping("/signIn")
    public String redirectToHome() {
        return "redirect:/";
    }

    @GetMapping("/find")
    public String findProducts(){
        return "find-products";

    }
}
