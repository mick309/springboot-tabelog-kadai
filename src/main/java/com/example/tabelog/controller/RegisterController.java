package com.example.tabelog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tabelog.form.UserCreateForm;
import com.example.tabelog.service.UserService;

import jakarta.validation.Valid;

@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        logger.info("Accessed /register");
        model.addAttribute("userCreateForm", new UserCreateForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid UserCreateForm userCreateForm,
                               BindingResult bindingResult,
                               Model model) {
        logger.info("Submitting registration form");
        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors occurred: {}", bindingResult.getAllErrors());
            return "register";
        }

        if (!userService.isSamePassword(userCreateForm.getPassword(), userCreateForm.getPasswordConfirmation())) {
            bindingResult.rejectValue("passwordConfirmation", "error.passwordConfirmation", "パスワードが一致しません");
            return "register";
        }

        try {
            userService.registerUser(userCreateForm);
            logger.info("User registered successfully");
            return "redirect:/register-success";
        } catch (IllegalArgumentException e) {
            logger.error("Registration failed: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
