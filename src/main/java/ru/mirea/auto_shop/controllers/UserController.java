package ru.mirea.auto_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mirea.auto_shop.DTO.UserRegistrationDto;
import ru.mirea.auto_shop.entities.User;
import ru.mirea.auto_shop.services.UserService;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/home"})
    public String homePage() {
        return "home";
    }

    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal UserDetails user, Model model) {
        User currentUser = userService.getUser(user.getUsername());
        model.addAttribute("username", currentUser.getName());
        model.addAttribute("orders", currentUser.getOrders());
        return "profile";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") UserRegistrationDto userDTO) {
        try {
            userService.add(userDTO);
        } catch(Exception e) {
            return "redirect:/registration?name_invalid";
        }
        return "redirect:/registration?success";
    }
}