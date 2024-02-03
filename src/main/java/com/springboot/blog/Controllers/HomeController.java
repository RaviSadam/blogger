package com.springboot.blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    // @GetMapping("/favicon.ico")
    // public String getFavicon(){
    //     return "redirect:/static/icon.ico";
    // } 

    @GetMapping("/")
    public RedirectView home(){
        return new RedirectView("/app/home");
    }

}
