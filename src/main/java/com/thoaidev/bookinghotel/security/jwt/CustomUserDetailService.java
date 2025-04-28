package com.thoaidev.bookinghotel.security.jwt;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.thoaidev.bookinghotel.model.Role;
import com.thoaidev.bookinghotel.model.UserEntity;
import com.thoaidev.bookinghotel.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{
    private final UserRepository userRepository;
    @Autowired
    public CustomUserDetailService(UserRepository userRepository){
        this.userRepository = userRepository;
        System.out.println("this CustomUserDetailService");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("UserName khoong được tìm thấy"));
        System.out.println("==println(CustomUserDetailService)LOAD USER FROM DB: " + user.getUsername());
        System.out.println("==println(CustomUserDetailService)PASSWORD HASH: " + user.getPassword());
        
        List<GrantedAuthority> authorities = user.getRoles()
        .stream()
        .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
        .collect(Collectors.toList());
        System.out.println("==ROLE: "+ authorities);
         
        return new User(user.getUsername(),
                        user.getPassword(),
                        mapRolesToAuthoritys(user.getRoles()));
    }
    
    private Collection<GrantedAuthority> mapRolesToAuthoritys(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
