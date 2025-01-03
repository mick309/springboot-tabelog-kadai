package com.example.tabelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tabelog.entity.GeneralUser;
import com.example.tabelog.service.GeneralUserService;

@Controller
@RequestMapping("/user")
public class GeneralUserController {

	private final GeneralUserService generalUserService;

	public GeneralUserController(GeneralUserService generalUserService) {
		this.generalUserService = generalUserService;
	}

	@GetMapping("/profile")
	public String myProfile(Model model) {
		GeneralUser currentUser = generalUserService.getCurrentUser();
		model.addAttribute("user", currentUser);
		return "user/profile";
	}

	@GetMapping("/profile/edit")
	public String editProfile(Model model) {
		GeneralUser currentUser = generalUserService.getCurrentUser();
		model.addAttribute("user", currentUser);
		return "user/edit";
	}

	@PostMapping("/profile/update")
	public String updateProfile(@ModelAttribute GeneralUser user) {
		generalUserService.updateCurrentUser(user);
		return "redirect:/user/profile";
	}

	@PostMapping("/profile/delete")
	public String deleteProfile() {
		generalUserService.deleteCurrentUser();
		return "redirect:/logout";
	}
}
