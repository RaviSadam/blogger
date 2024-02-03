package com.springboot.blog.Controllers;

// ravi123
// Ravi@123
// rama123
// Rama@123

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.springboot.blog.Dto.AddNewTitleDto;
import com.springboot.blog.Dto.BlogData;
import com.springboot.blog.Dto.BlogTitleDto;
import com.springboot.blog.Dto.MyArticlesDto;
import com.springboot.blog.Projections.BlogTitleProjection;
import com.springboot.blog.Projections.MyArticlesProjection;
import com.springboot.blog.Services.BlogService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotBlank;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    
    @GetMapping("/add-tag")
    @RolesAllowed({"ROLE_ADMIN"})
    public ModelAndView addCategory(){
        ModelAndView model=new ModelAndView();
        model.addObject("exists",false);
        model.addObject("existsText","");
        model.addObject("success",false);
        model.addObject("successText","");
        model.setViewName("add-tag");
        model.setStatus(HttpStatus.OK);
        return model;
    }


    @PostMapping("/add-tag")
    @RolesAllowed({"ROLE_ADMIN"})
    public ModelAndView addCategoryPost(@NotBlank @RequestParam("categeory") String categeory){
        ModelAndView model=new ModelAndView("add-tag");
        boolean bool=blogService.addCategory(categeory);
        if(bool){
            model.addObject("exists",false);
            model.addObject("existsText","");
            model.addObject("success",true);
            model.addObject("successText",categeory+" added");
        }
        else{
            model.addObject("exists",true);
            model.addObject("existsText",categeory+" already exists");
            model.addObject("success",false);
            model.addObject("successText","");
        }
        return model;
    }

    //request for title page
    @GetMapping("/add-title")
    @RolesAllowed({"ROLE_ADMIN"})
    public ModelAndView addNewTitleGet(){
        ModelAndView model=new ModelAndView("add-title");
        model.addObject("tags",blogService.getAllTages());
        model.addObject("error",false);
        model.addObject("errorText","");
        model.addObject("success",false);
        model.addObject("successText","");
        return model;
    }

    //adding new title
    @PostMapping("/add-title")
    @RolesAllowed({"ROLES_ADMIN"})
    public ModelAndView addNewTitlePost(@ModelAttribute AddNewTitleDto addNewTitleDto){
        ModelAndView model=new ModelAndView("add-title");
        boolean bool=blogService.addNewTitle(addNewTitleDto);
        model.addObject("tags",blogService.getAllTages());
        if(bool){
            model.addObject("success",true);
            model.addObject("successText","Title added");
        }
        else{
            model.addObject("error",true);
            model.addObject("errorText","Unable to add title");
        }
        return model;
    }


    //request for pick article page
    @GetMapping("/pick-article")
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public ModelAndView pickArticleGet(
                                @RequestParam(value = "pageNumber",required = false,defaultValue = "1") int pageNumber,
                                @RequestParam(value="category",required = false,defaultValue = "random") String category,
                                HttpServletResponse response
    ){
        ModelAndView model=new ModelAndView("pick-article");
        Page<BlogTitleProjection> titels=blogService.getBlogTitles(category, pageNumber);
        model.addObject("currentCategeory",category);
        model.addObject("currentPage",pageNumber);
        model.addObject("totalPages",titels.getTotalPages());

        //tags with count
        model.addObject("categories",blogService.getAllTagsWithCnt());
        
        //getBlogTitles will retunr List of BLogTitleDto Objects
        model.addObject("articles",titels.stream().map(title->BlogTitleDto.builder().id(title.getId()).explanation(title.getExplanation()).category(title.getCategory()).title(title.getTitle()).build()).collect(Collectors.toList()));
        return model;
    }

    @GetMapping("/pick/{blogId}/{blogTitle}")
    public RedirectView pickArticle(RedirectAttributes redirectAttributes,@PathVariable("blogId") String blogId,@PathVariable("blogTitle") String blogTitle,@AuthenticationPrincipal UserDetails user){
        RedirectView redirectView=new RedirectView("/blog/write");
        blogService.pickArticle(blogId,user.getUsername());
        redirectAttributes.addAttribute("blogTitle", blogTitle);
        redirectAttributes.addAttribute("blogId", blogId);
        return redirectView;
    }

    @GetMapping("/write")
    public ModelAndView writeBlog(@AuthenticationPrincipal UserDetails user,@RequestParam Map<String,String> params){ 
        ModelAndView model=new ModelAndView();
        if(!(params.containsKey("blogId") && params.containsKey("blogTitle"))){
            model.setViewName("redirect:/blog/my-articles");
            return model;
        }   

        model.addObject("blogId",params.get("blogId"));
        model.addObject("blogTitle",params.get("blogTitle"));
        model.addObject("isLoggedIn",user!=null);
        model.addObject("readOnly",false);
        model.addObject("comments",null);
        model.setViewName("write");
        return model;
    }

    @PostMapping("/review")
    public RedirectView reviewBlog(@ModelAttribute BlogData blogData) throws IOException{
        if(!(blogData.getBlogId()!=null && blogData.getContent()!=null)){
            throw new DataIntegrityViolationException("Requried Fields not present in request");
        }
        RedirectView redirectView=new RedirectView("/blog/my-articles");
        try {
            blogService.reviewBlog(blogData);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
        return redirectView;
    }

    @GetMapping("/my-articles")
    public ModelAndView myArticles(@RequestParam(value = "pageNumber",defaultValue = "1",required =false) int pageNumber,
                            @AuthenticationPrincipal UserDetails userDetails
    ){
        ModelAndView model=new ModelAndView("my-articles");
        Page<MyArticlesProjection> pages=blogService.getMyArticles(userDetails.getUsername(),pageNumber);
        model.addObject("articles",pages.stream().map(projection-> MyArticlesDto.builder().dueDate(new Date(projection.getPickedDate().getTime()+5*24*60*60*1000)).id(projection.getId()).title(projection.getTitle()).status(projection.getStatus()).build()).collect(Collectors.toList()));
        model.addObject("totalPages",pages.getTotalPages());
        model.addObject("currentPage",pageNumber);
        model.addObject("isLoggedIn",userDetails!=null);
        return model;
    }

    @GetMapping("/discard")
    public RedirectView discardArticle(@RequestParam(value = "blogId",required = true)String blogId){
        blogService.discardArticle(blogId);
        RedirectView redirectView=new RedirectView("my-articles");
        return redirectView;
    }

    @GetMapping("/images")
    public ModelAndView images(@AuthenticationPrincipal UserDetails userDetails){       
        ModelAndView model=new ModelAndView("images");
        model.addObject("isLoggedIn",userDetails!=null);
        model.addObject(model);
        return model;
    }
}
