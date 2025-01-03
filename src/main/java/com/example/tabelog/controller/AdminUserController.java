package com.example.tabelog.controller;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.Role;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.UserCreateForm;
import com.example.tabelog.form.UserEditForm;
import com.example.tabelog.repository.UserRepository;
import com.example.tabelog.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

	private final UserRepository userRepository;
	private final UserService userService;

	public AdminUserController(UserRepository userRepository, UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}

	@GetMapping
	public String index(
			@RequestParam(name = "keyword", required = false) String keyword,
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model,
			HttpServletRequest request) {

		CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");

		Page<User> userPage = (keyword != null && !keyword.isEmpty())
				? userRepository.findByNameLikeOrFuriganaLike("%" + keyword + "%", "%" + keyword + "%", pageable)
				: userRepository.findAll(pageable);

		model.addAttribute("userPage", userPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("token", csrfToken != null ? csrfToken.getToken() : null);

		return "admin/users/index";
	}

	@GetMapping("/{id}/edit")
	public String editUser(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		User user = userService.findById(id);
		if (user == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "ユーザーが見つかりませんでした。");
			return "redirect:/admin/users";
		}

		Long roleId = user.getRoles().stream().findFirst()
				.map(Role::getId)
				.orElse(null);

		UserEditForm userEditForm = new UserEditForm(
				user.getId(),
				user.getName(),
				user.getFurigana(),
				user.getPostalCode(),
				user.getAddress(),
				user.getPhoneNumber(),
				user.getEmail(),
				roleId);
		model.addAttribute("userEditForm", userEditForm);
		return "admin/users/edit";
	}

	@PostMapping("/{id}/edit")
	public String updateUser(@PathVariable Long id, @ModelAttribute @Valid UserEditForm userEditForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/users/edit";
		}

		userService.update(userEditForm);
		redirectAttributes.addFlashAttribute("successMessage", "会員情報が更新されました。");
		return "redirect:/admin/users";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
		try {
			userRepository.deleteById(id);
			redirectAttributes.addFlashAttribute("successMessage", "会員が削除されました。");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "会員の削除に失敗しました: " + e.getMessage());
		}
		return "redirect:/admin/users";
	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("userCreateForm", new UserCreateForm());
		return "admin/users/register";
	}

	@PostMapping("/new")
	public String createUser(@ModelAttribute("userCreateForm") @Valid UserCreateForm form,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "admin/users/register";
		}

		if (!form.getPassword().equals(form.getPasswordConfirmation())) {
			bindingResult.rejectValue("passwordConfirmation", "error.passwordConfirmation", "パスワードが一致しません");
			return "admin/users/register";
		}

		try {
			userService.registerUser(form);
			redirectAttributes.addFlashAttribute("successMessage", "ユーザーが作成されました");
		} catch (Exception e) {
			bindingResult.rejectValue("email", "error.email", "メールアドレスが既に使用されています。");
			return "admin/users/register";
		}
		return "redirect:/admin/users";
	}

	@GetMapping("/{id}")
	public String showUserDetails(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		User user = userService.findById(id);
		if (user == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "ユーザーが見つかりませんでした。");
			return "redirect:/admin/users";
		}
		model.addAttribute("user", user);
		model.addAttribute("roles", user.getRoles().stream()
				.map(Role::getName)
				.collect(Collectors.joining(", ")));
		return "admin/users/show";
	}
}
