package com.springboot.blog.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.springboot.blog.Services.ServiceImplement.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor; 

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final String PUBLIC_URLS[]={"/favicon.ico","/app/**","/actuator/**","/h2/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf((csrf)->
                csrf.disable()
            )
            .authorizeHttpRequests((auth)->
                auth    
                    .requestMatchers(PUBLIC_URLS).permitAll()
                    .requestMatchers(HttpMethod.GET,"/").permitAll()
                    .anyRequest().authenticated()
            )
            .logout((logout)->
                logout
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
            )
            .authenticationProvider(authenticationProvider())
            .formLogin(form->
                form
                    .defaultSuccessUrl("/app/home", false)
                    .usernameParameter("username")
                    .passwordParameter("password")
            )
            .logout((logout)->
                logout
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/login")
            )
            ;
            // .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
     
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        authenticationProvider.setPasswordEncoder(passwordEncoder); 
        return authenticationProvider; 
    }
}
