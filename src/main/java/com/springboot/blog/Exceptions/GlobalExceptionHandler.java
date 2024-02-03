package com.springboot.blog.Exceptions;

import org.hibernate.JDBCException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    //registration exp
    @ExceptionHandler(UserExistanceException.class)
    public String  userExistanceException(HttpServletRequest request,RedirectAttributes redirectAttributes,UserExistanceException ex,@AuthenticationPrincipal UserDetails userDetails){
        return this.helper(redirectAttributes, ex.getMessage(), HttpStatus.CONFLICT, request.getServletPath(),request.getMethod(),userDetails!=null);
    }

    //validation exp BY Spring boot validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String methodArgumentNotValidException(HttpServletRequest request,RedirectAttributes redirectAttributes,MethodArgumentNotValidException ex,@AuthenticationPrincipal UserDetails userDetails){
        List<ObjectError> errors=ex.getAllErrors();
        StringBuilder builder=new StringBuilder();
        for(ObjectError error:errors){
            builder.append(error.getDefaultMessage()+"\n");
        }
        return this.helper(redirectAttributes, builder.toString(), HttpStatus.BAD_REQUEST, request.getServletPath(),request.getMethod(),userDetails!=null);
    }

    //jdbc exc
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String dataIntegrityViolationException(HttpServletRequest request,RedirectAttributes redirectAttributes,DataIntegrityViolationException ex,@AuthenticationPrincipal UserDetails userDetails){
        return this.helper(redirectAttributes, ex.getMessage(), HttpStatus.BAD_REQUEST, request.getServletPath(), request.getMethod(), userDetails!=null);
    }

    //sprign security exception
    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedException(HttpServletRequest request,RedirectAttributes redirectAttributes,AccessDeniedException  ex,@AuthenticationPrincipal UserDetails userDetails){
        return this.helper(redirectAttributes, ex.getMessage(), HttpStatus.FORBIDDEN, request.getServletPath(), request.getMethod(), userDetails!=null);
    } 


    //page not found exp
    @ExceptionHandler(NoHandlerFoundException.class)
    public String notFount(HttpServletRequest request,RedirectAttributes redirectAttributes,NoHandlerFoundException  ex,@AuthenticationPrincipal UserDetails userDetails){
        return this.helper(redirectAttributes, ex.getMessage(), HttpStatus.NOT_FOUND, request.getServletPath(), request.getMethod(), userDetails!=null);
    }


    //JDBC Exp
    @ExceptionHandler(JDBCException.class)
    public String jdbcException(HttpServletRequest request,RedirectAttributes redirectAttributes,JDBCException  ex,@AuthenticationPrincipal UserDetails userDetails){
        return this.helper(redirectAttributes, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request.getServletPath(), request.getMethod(), userDetails!=null);
    }

    //io excaption zip conversion
    @ExceptionHandler(IOException.class)
    public String ioException(HttpServletRequest request,RedirectAttributes redirectAttributes,IOException ex,@AuthenticationPrincipal UserDetails userDetails){
        return this.helper(redirectAttributes, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request.getServletPath(), request.getMethod(), userDetails!=null);
    }

    @ExceptionHandler(Exception.class)
    public String exception(HttpServletRequest request,RedirectAttributes redirectAttributes,Exception ex,@AuthenticationPrincipal UserDetails userDetails){
        return this.helper(redirectAttributes, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request.getServletPath(), request.getMethod(), userDetails!=null);
    }


    //helper
    private String helper(RedirectAttributes redirectAttributes,String message,HttpStatusCode statusCode,String requestPath,String requestMethod,boolean isLoggedIn){
        log.error(message);
        redirectAttributes.addFlashAttribute("errorMessage",message);
        redirectAttributes.addFlashAttribute("errorCode",statusCode);
        redirectAttributes.addFlashAttribute("isLoggedIn",isLoggedIn);
        redirectAttributes.addFlashAttribute("requestPath",requestPath);
        redirectAttributes.addFlashAttribute("requestMethod",requestMethod);
        return "redirect:/app/error";
    }

}
