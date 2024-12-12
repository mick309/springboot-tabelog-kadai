package com.example.tabelog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tabelog.entity.Company;
import com.example.tabelog.entity.User;
import com.example.tabelog.security.UserDetailsImpl;
import com.example.tabelog.service.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {

	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@GetMapping("/view")
	public String viewCompanyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
		// 認証ユーザーを取得
		User user = userDetails.getUser();

		// 会社情報を取得
		Company company = companyService.getCompanyInfo();

		// ユーザーのロールに応じて情報を設定
		switch (user.getRole().getName()) {
		case "ROLE_PREMIUM_USER":
			company.setPremiumInfo("プレミアム会員向けの特別な情報");
			break;
		case "ROLE_GENERAL":
			// 一般ユーザー向けの追加情報を必要に応じて設定
			company.setDescription("一般ユーザー向けの会社概要です。");
			break;
		default:
			// 管理者またはその他のロールに特別な処理が必要であれば追加
			break;
		}

		// モデルに会社情報とユーザー情報を追加
		model.addAttribute("company", company);
		model.addAttribute("user", user);
		return "general/company/view"; // HTMLのパス
	}
}
