package com.springboot.blog;



import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

// ravi123
// ravi@123 -- ADMIN, USER

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import jakarta.annotation.PostConstruct;


@SpringBootApplication
@EnableWebMvc
@EnableMethodSecurity
@EnableCaching
public class BlogApplication {


	//todays date;
	public static Date todaysDate;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BlogApplication.class, args);
	}

	@PostConstruct
	public void init(){
		BlogApplication.todaysDate=new Date(System.currentTimeMillis());
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder(12);
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    }
	
    @Bean
    public SimpleUrlHandlerMapping customFaviconHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
		Map<String,ResourceHttpRequestHandler> map=Collections.singletonMap("/favicon.ico", faviconRequestHandler());
        if(map!=null)
			mapping.setUrlMap(map);
        return mapping;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        return new InternalResourceViewResolver();
    }

    @Bean
    protected ResourceHttpRequestHandler faviconRequestHandler() {
        ResourceHttpRequestHandler requestHandler
          = new ResourceHttpRequestHandler();
        ClassPathResource classPathResource 
          = new ClassPathResource("/static");
        List<Resource> locations = Arrays.asList(classPathResource);
		if(locations!=null)
        	requestHandler.setLocations(locations);
        return requestHandler;
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2/**"));
    }
}