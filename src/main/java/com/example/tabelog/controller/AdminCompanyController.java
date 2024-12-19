package com.example.tabelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tabelog.entity.AdminCompany;
import com.example.tabelog.form.AdminCompanyForm;
import com.example.tabelog.service.AdminCompanyService;

@Controller
@RequestMapping("/admin/company")
public class AdminCompanyController {

	private final AdminCompanyService adminCompanyService;

	public AdminCompanyController(AdminCompanyService adminCompanyService) {
		this.adminCompanyService = adminCompanyService;

	}

	// 会社情報の表示
	@GetMapping
	public String viewCompany(Model model) {
		AdminCompany company = adminCompanyService.getSingleCompany().orElse(new AdminCompany());
		model.addAttribute("company", company);
		return "admin/company/view";
	}

	// 編集画面の表示
	@GetMapping("/edit")
	public String editCompany(Model model) {
		AdminCompany company = adminCompanyService.getSingleCompany().orElse(new AdminCompany());
		AdminCompanyForm form = AdminCompanyForm.fromEntity(company);
		model.addAttribute("companyForm", form);
		return "admin/company/edit";
	}

	@PostMapping("/edit")
	public String updateCompany(@ModelAttribute("companyForm") AdminCompanyForm companyForm) {
		System.out.println("Received form data: " + companyForm); // デバッグログ
		AdminCompany updatedCompany = companyForm.toEntity();
		adminCompanyService.updateCompanyInfo(updatedCompany);
		return "redirect:/admin/company";
	}
}