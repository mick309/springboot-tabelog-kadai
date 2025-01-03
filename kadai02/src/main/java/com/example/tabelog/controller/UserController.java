package com.example.tabelog.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.Role;
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

<<<<<<< HEAD
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
		User user = userService.findById(userDetails.getUser().getId());
		model.addAttribute("user", user);
		return "user/index";
	}

	@GetMapping("/edit")
	public String editUser(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
		User authenticatedUser = userService.findById(userDetails.getUser().getId());

		// 複数ロール対応
		Long roleId = authenticatedUser.getRoles().stream()
				.findFirst()
				.map(Role::getId)
				.orElse(null);

		UserEditForm userEditForm = new UserEditForm(
				authenticatedUser.getId(),
				authenticatedUser.getName(),
				authenticatedUser.getFurigana(),
				authenticatedUser.getPostalCode(),
				authenticatedUser.getAddress(),
				authenticatedUser.getPhoneNumber(),
				authenticatedUser.getEmail(),
				roleId);

		model.addAttribute("roles", userService.findAllRoles());
		model.addAttribute("userEditForm", userEditForm);

		return "user/edit";
	}

	@PostMapping("/update")
	public String update(
			@ModelAttribute @Validated UserEditForm userEditForm,
			BindingResult bindingResult,
			@AuthenticationPrincipal UserDetailsImpl userDetails,
			RedirectAttributes redirectAttributes) {

		if (userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())) {
			bindingResult.rejectValue("email", null, "このメールアドレスは既に登録されています。");
		}

		if (bindingResult.hasErrors()) {
			return "user/edit";
		}

		try {
			userService.update(userEditForm);
			redirectAttributes.addFlashAttribute("successMessage", "会員情報を更新しました。");
			return "redirect:/user";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "ユーザー情報の更新に失敗しました。");
			return "redirect:/user/edit";
		}
	}

=======
	// ユーザー情報を表示
	@GetMapping
	public String index(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
		User user = userService.findById(userDetails.getUser().getId());
		model.addAttribute("user", user);
		return "user/index";
	}

	// ユーザー情報の編集画面
	@GetMapping("/edit")
	public String editUser(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
		User authenticatedUser = userService.findById(userDetails.getUser().getId());

		// 最初のロールを取得
		Long roleId = authenticatedUser.getRoles().stream()
				.findFirst()
				.map(Role::getId)
				.orElse(null);

		UserEditForm userEditForm = new UserEditForm(
				authenticatedUser.getId(),
				authenticatedUser.getName(),
				authenticatedUser.getFurigana(),
				authenticatedUser.getPostalCode(),
				authenticatedUser.getAddress(),
				authenticatedUser.getPhoneNumber(),
				authenticatedUser.getEmail(),
				roleId);

		model.addAttribute("roles", userService.findAllRoles());
		model.addAttribute("userEditForm", userEditForm);

		return "user/edit";
	}

	// ユーザー情報の更新
	@PostMapping("/update")
	public String update(
			@ModelAttribute @Validated UserEditForm userEditForm,
			BindingResult bindingResult,
			@AuthenticationPrincipal UserDetailsImpl userDetails,
			RedirectAttributes redirectAttributes) {

		// メールアドレスが変更され、すでに登録済みの場合
		if (userService.isEmailChanged(userEditForm) && userService.isEmailRegistered(userEditForm.getEmail())) {
			bindingResult.rejectValue("email", null, "このメールアドレスは既に登録されています。");
		}

		// 入力エラーがある場合
		if (bindingResult.hasErrors()) {
			return "user/edit";
		}

		try {
			// 更新処理
			userService.update(userEditForm);
			redirectAttributes.addFlashAttribute("successMessage", "会員情報を更新しました。");
			return "redirect:/user";
		} catch (Exception e) {
			// 更新失敗時
			redirectAttributes.addFlashAttribute("errorMessage", "ユーザー情報の更新に失敗しました。");
			return "redirect:/user/edit";
		}
	}

	// ユーザー削除
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
	@PostMapping("/delete")
	public String delete(
			@AuthenticationPrincipal UserDetailsImpl userDetails,
			RedirectAttributes redirectAttributes) {
		try {
			Long userId = userDetails.getUser().getId();
			userService.delete(userId);
			redirectAttributes.addFlashAttribute("successMessage", "会員情報を削除しました。");
			return "redirect:/logout";
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "ユーザー情報の削除に失敗しました。");
			return "redirect:/user";
		}
	}

	@GetMapping("/debug/roles")
	@ResponseBody
	public String debugRoles(Authentication authentication) {
		return authentication.getAuthorities().toString();
	}
}
