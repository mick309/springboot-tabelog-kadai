package com.example.tabelog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
<<<<<<< HEAD
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.User;
import com.example.tabelog.form.UserEditForm;
import com.example.tabelog.security.UserDetailsImpl;
import com.example.tabelog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	// ユーザー情報を表示
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userService.getAuthenticatedUser(userDetailsImpl.getUser().getId());
		model.addAttribute("user", user);
		return "user/index";
	}

	// ユーザー情報の編集画面
	@GetMapping("/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userService.getAuthenticatedUser(userDetailsImpl.getUser().getId());
		UserEditForm userEditForm = new UserEditForm(
				user.getId(), user.getName(), user.getFurigana(),
				user.getPostalCode(), user.getAddress(),
				user.getPhoneNumber(), user.getEmail());
		model.addAttribute("userEditForm", userEditForm);
		return "user/edit";
	}

	// ユーザー情報の更新
	@PostMapping("/update")
	public String update(
			@ModelAttribute @Validated UserEditForm userEditForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		// メールアドレスが変更された場合に、既に登録されている場合はエラーメッセージを表示
		if (userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())) {
			bindingResult.rejectValue("email", null, "すでに登録済みのメールアドレスです。");
		}

		// 入力にエラーがある場合、編集画面に戻す
		if (bindingResult.hasErrors()) {
			return "user/edit";
		}

		// ユーザー情報の更新処理
		try {
			userService.update(userEditForm);
			redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");
			return "redirect:/user";
		} catch (Exception e) {
			// 更新処理に失敗した場合
			redirectAttributes.addFlashAttribute("errorMessage", "ユーザー情報の更新に失敗しました。");
			return "redirect:/user/edit";
		}
	}
}
=======
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.User;
import com.example.tabelog.form.UserEditForm;
import com.example.tabelog.repository.UserRepository;
import com.example.tabelog.security.UserDetailsImpl;
import com.example.tabelog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private final UserRepository userRepository;
	private final UserService userService;

	public UserController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;

	}

	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());

		model.addAttribute("user", user);

		return "user/index";
	}

	@GetMapping("/edit")
	public String edit(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl, Model model) {
		User user = userRepository.getReferenceById(userDetailsImpl.getUser().getId());
		UserEditForm userEditForm = new UserEditForm(user.getId(), user.getName(), user.getFurigana(),
				user.getPostalCode(), user.getAddress(), user.getPhoneNumber(), user.getEmail());

		model.addAttribute("userEditForm", userEditForm);

		return "user/edit";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute @Validated UserEditForm userEditForm, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		// メールアドレスが変更されており、かつ登録済みであれば、BindingResultオブジェクトにエラー内容を追加する
		if (userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())) {
			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "email", "すでに登録済みのメールアドレスです。");
			bindingResult.addError(fieldError);
		}

		if (bindingResult.hasErrors()) {
			return "user/edit";
		}

		userService.update(userEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "会員情報を編集しました。");

		return "redirect:/user";
	}

}
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
