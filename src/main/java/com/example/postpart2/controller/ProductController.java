package com.example.postpart2.controller;


import com.example.postpart2.dto.ProductRequestDto;
import com.example.postpart2.entity.User;
import com.example.postpart2.entity.UserRoleEnum;
import com.example.postpart2.security.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/products")
    public String getProducts(@AuthenticationPrincipal UserDetailsImpl postDetails) {
        //Authentication 의 Principle
        User post = postDetails.getUser();
        System.out.println("user.getUsername() = " + post.getUsername());
//        System.out.println("user.getEmail() = " + post.getEmail());

        return "redirect:/";
    }

    @Secured(UserRoleEnum.Authority.ADMIN) // 관리자용
    @GetMapping("/products/secured")
    public String getProductsByAdmin(@AuthenticationPrincipal UserDetailsImpl postDetails) {
        System.out.println("userDetails.getUsername() = " + postDetails.getUsername());
        for (GrantedAuthority authority : postDetails.getAuthorities()) {
            System.out.println("authority.getAuthority() = " + authority.getAuthority());
        }

        return "redirect:/";
    }

    @PostMapping("/validation")
    @ResponseBody
    public ProductRequestDto testValid(@RequestBody @Valid ProductRequestDto requestDto) {
        return requestDto;
    }


}