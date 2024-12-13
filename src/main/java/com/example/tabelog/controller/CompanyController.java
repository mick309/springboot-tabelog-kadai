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
	    User user = userDetails.getUser();
	    Company company = companyService.getCompanyInfo();

	    model.addAttribute("company", company);
	    model.addAttribute("user", user);

	    // ユーザーのロールに基づいて適切なテンプレートを選択
	    switch (user.getRole().getName()) {
	        case "ROLE_PREMIUM_USER":
	        case "ROLE_GENERAL":
	            return "company/company"; // 一般・課金ユーザー共通
	        case "ROLE_ADMIN":
	            return "admin/company/view"; // 管理者専用
	        default:
	            throw new IllegalStateException("予期しないロールです");
	    }
	}

	}

