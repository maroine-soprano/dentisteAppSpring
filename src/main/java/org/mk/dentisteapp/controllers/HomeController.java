package org.mk.dentisteapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.mk.dentisteapp.entities.User;
import org.mk.dentisteapp.services.DentisteService;
import org.mk.dentisteapp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Collection;

@RestController
public class HomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    DentisteService dentisteService;

    @PostMapping("/login")
    public String generateToken(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password or not active");
        }
        return jwtUtil.generateToken(user.getEmail());
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> store(@RequestParam MultipartFile photo
            , @RequestParam MultipartFile carte, @RequestParam String dentiste
            , @RequestParam String user) throws JsonProcessingException {
        return dentisteService.add(photo,carte,dentiste,user);
    }

    @GetMapping("/role")
    public String getRole(Principal principal){
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return principal.getName()+authorities.toArray()[0];
    }
}
