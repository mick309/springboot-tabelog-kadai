package com.example.tabelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.tabelog.form.UserCreateForm;
import com.example.tabelog.service.UserService;

@Controller
public class RegisterController {

	private final UserService userService;

	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("userCreateForm", new UserCreateForm());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute UserCreateForm userCreateForm, Model model) {
		// パスワードと確認パスワードが一致しているか確認
		if (!userService.isSamePassword(userCreateForm.getPassword(), userCreateForm.getPasswordConfirmation())) {
			model.addAttribute("error", "パスワードが一致しません");
			return "register";
		}

		try {
			userService.registerUser(userCreateForm);
			return "redirect:/register-success";
		} catch (IllegalArgumentException e) {
			model.addAttribute("error", e.getMessage());
			return "register";
		}
	}
}
