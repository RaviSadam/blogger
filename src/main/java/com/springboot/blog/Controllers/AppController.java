package com.springboot.blog.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.blog.Dto.UserRegistration;
import com.springboot.blog.Services.AppService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/app")
public class AppController {
    
    @Autowired
    private AppService appService;

    //registration process
    @GetMapping("/register")
    public String getRegisteration(Model model){
        model.addAttribute("roles",appService.getAllRoles());
        return "registration";
    }
    
    @PostMapping("/registration")
    public String postRegisteration(@Valid @ModelAttribute UserRegistration userRegistration){
        appService.registration(userRegistration);
        return "redirect:/login";
    }


    @GetMapping("/error")
    public String errorPage(Model model,RedirectAttributes redirectAttributes){
        model.addAllAttributes(redirectAttributes.getFlashAttributes());
        redirectAttributes.getFlashAttributes().clear();
        return "error";
    }

    @GetMapping("/home")
    public ModelAndView home(@RequestParam(value="pageNumber",required = false,defaultValue = "0") int pageNumber,
                            @RequestParam(value="pageSize",required = false,defaultValue = "10") int pageSize,
                            @AuthenticationPrincipal UserDetails user
                            ){
        ModelAndView modelAndView=new ModelAndView("home");
        modelAndView.addObject("articles",appService.getHomeArticles(pageNumber,pageSize));
        modelAndView.addObject("isLoggedIn",user!=null);
        return modelAndView;
    }

    @GetMapping("/read/{id}")
    public ModelAndView reading(@PathVariable("id")String id,@AuthenticationPrincipal UserDetails user){
        ModelAndView modelAndView=new ModelAndView("read");
        String username="";
        if(user!=null)
            username=user.getUsername();
        modelAndView.addObject("blog",appService.getBlogData(id,username));
        System.out.println(modelAndView.getModel());
        return modelAndView;
    }
}
