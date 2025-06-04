package com.mover.controllers;

import java.security.Principal;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login"; // maps to login.html
    }

    @GetMapping("/error")
    public String error() {
        return "error"; // maps to error.html
    }

    @GetMapping("/Mover")
    public String homePage(Model model, Principal principal) {
        if (principal instanceof OAuth2AuthenticationToken oauthToken) {
            OAuth2User oauthUser = oauthToken.getPrincipal();
            String name = oauthUser.getAttribute("name"); // or "email"
            model.addAttribute("username", name);
        } else {
            model.addAttribute("username", principal.getName());
        }
        return "Mover";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout"; // maps to logout.html
    }
}
