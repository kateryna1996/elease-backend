//package nl.klev.eleasebackend.services;
//
//import nl.klev.eleasebackend.dtos.UserDto;
//import nl.klev.eleasebackend.models.Authority;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private UserService userService;
//
//    public CustomUserDetailsService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        UserDto userDto = userService.getUserByUsername(username);
//
//        String password = userDto.getPassword();
//
//        Set<Authority> authorities = userDto.getAuthorities();
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Authority authority: authorities) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
//        }
//
//        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
//    }
//}