// RegisterController.java
package com.example.tabelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.form.UserCreateForm;
import com.example.tabelog.service.UserService;

@Controller
public class RegisterController {

	private final UserService userService;

	public RegisterController(UserService userService) {
		this.userService = userService;
	}

	// 新規会員登録画面表示
	@GetMapping("/register")
	public String newUser(Model model) {
		model.addAttribute("userCreateForm", new UserCreateForm());
		model.addAttribute("roles", userService.findAllRoles()); // ロール情報を設定
		return "admin/user/register";
	}

	// 新規会員登録処理
	@PostMapping("/register")
	public String registerUser(
			@ModelAttribute @Validated UserCreateForm userCreateForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			Model model) {

		// メールアドレスが既に登録済みの場合のチェック
		if (userService.isEmailRegistered(userCreateForm.getEmail())) {
			bindingResult.rejectValue("email", null, "このメールアドレスは既に登録されています。");
		}

		// パスワードと確認用パスワードの一致チェック
		if (!userService.isSamePassword(userCreateForm.getPassword(), userCreateForm.getPasswordConfirmation())) {
			bindingResult.rejectValue("password", null, "パスワードが一致しません。");
		}

		// 入力エラーがある場合
		if (bindingResult.hasErrors()) {
			model.addAttribute("roles", userService.findAllRoles());
			return "admin/user/register";
		}

		// ユーザー登録処理
		try {
			userService.registerUser(userCreateForm); // 修正: UserCreateForm を渡す
			redirectAttributes.addFlashAttribute("successMessage", "新規会員を登録しました。");
			return "redirect:/admin/users";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "ユーザー登録に失敗しました。");
			return "redirect:/admin/users/register";
		}
	}
}
