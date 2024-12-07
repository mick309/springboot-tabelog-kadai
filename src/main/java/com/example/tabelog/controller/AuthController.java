package com.example.tabelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.User;
import com.example.tabelog.entity.VerificationToken;
import com.example.tabelog.event.SignupEventPublisher;
import com.example.tabelog.form.SignupForm;
import com.example.tabelog.service.UserService;
import com.example.tabelog.service.VerificationTokenService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

	private final UserService userService;
	private final SignupEventPublisher signupEventPublisher;
	private final VerificationTokenService verificationTokenService;

	public AuthController(
			UserService userService,
			SignupEventPublisher signupEventPublisher,
			VerificationTokenService verificationTokenService) {
		this.userService = userService;
		this.signupEventPublisher = signupEventPublisher;
		this.verificationTokenService = verificationTokenService;
	}

	// ログインページ表示
	@GetMapping("/login")
	public String login() {
		return "auth/login";
	}

	// サインアップページ表示
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return "auth/signup";
	}

	// サインアップ処理
	@PostMapping("/signup")
	public String signup(
			@ModelAttribute @Validated SignupForm signupForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest) {

		// メールアドレスが登録済みの場合のエラーチェック
		if (userService.isEmailRegistered(signupForm.getEmail())) {
			bindingResult.addError(new FieldError(
					bindingResult.getObjectName(),
					"email",
					"すでに登録済みのメールアドレスです。"));
		}

		// パスワード確認用チェック
		if (!userService.isSamePassword(signupForm.getPassword(), signupForm.getPasswordConfirmation())) {
			bindingResult.addError(new FieldError(
					bindingResult.getObjectName(),
					"passwordConfirmation",
					"パスワードが一致しません。"));
		}

		// 入力エラーがある場合はサインアップ画面へ戻る
		if (bindingResult.hasErrors()) {
			return "auth/signup";
		}

		// ユーザー作成処理
		User createdUser = userService.create(signupForm);

		// サインアップイベントを発行
		String requestUrl = httpServletRequest.getRequestURL().toString();
		signupEventPublisher.publishSignupEvent(createdUser, requestUrl);

		// リダイレクト用の成功メッセージ設定
		redirectAttributes.addFlashAttribute("successMessage",
				"ご入力いただいたメールアドレスに認証メールを送信しました。メールに記載されているリンクをクリックし、会員登録を完了してください。");

		return "redirect:/";
	}

	// サインアップ認証処理
	@GetMapping("/signup/verify")
	public String verify(@RequestParam(name = "token") String token, Model model) {
		VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);

		if (verificationToken != null) {
			User user = verificationToken.getUser();
			userService.enableUser(user); // ユーザーを有効化
			model.addAttribute("successMessage", "会員登録が完了しました。");
		} else {
			model.addAttribute("errorMessage", "トークンが無効です。");
		}

		return "auth/verify";
	}
}
