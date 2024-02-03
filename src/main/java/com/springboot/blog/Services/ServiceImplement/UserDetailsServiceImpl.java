package com.springboot.blog.Services.ServiceImplement;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.blog.Models.User;
import com.springboot.blog.Projections.UserAuthProjection;
import com.springboot.blog.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthProjection userAuthProjection=userRepository.getUserForAuth(username);
        if(userAuthProjection==null)
            throw new UsernameNotFoundException("user not found");
        return User
                .builder()
                .username(username)
                .password(userAuthProjection.getPassword())
                .authorities(
                    Arrays.asList(
                        userAuthProjection.getRolename().split(" ")
                    )
                    .stream()
                    .map(
                        rolename->new SimpleGrantedAuthority(rolename)
                    )
                    .collect(
                        Collectors.toSet()
                    )
                )
                .build();
    } 
}
