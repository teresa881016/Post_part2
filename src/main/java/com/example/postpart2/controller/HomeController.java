package com.example.postpart2.controller;


import com.example.postpart2.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl postDetails) {
        model.addAttribute("username", postDetails.getUsername());
        return "index"; // main page인 index로 반환
    }
}