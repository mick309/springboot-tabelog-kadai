package com.example.tabelog.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.AdminCompany;
import com.example.tabelog.service.AdminCompanyService;

@Controller
@RequestMapping("/admin/company")
public class AdminCompanyController {

	private final AdminCompanyService service;

	public AdminCompanyController(AdminCompanyService service) {
		this.service = service;
	}

	@GetMapping
	public String viewCompanyInfo(Model model) {
		AdminCompany company = service.getSingleCompany()
				.orElseThrow(() -> new RuntimeException("会社情報が存在しません"));
		model.addAttribute("company", company);
		return "admin/company/view";
	}

	@PutMapping("/{id}")
	public ResponseEntity<AdminCompany> updateCompanyInfo(@PathVariable Long id,
			@RequestBody AdminCompany updatedInfo) {
		return ResponseEntity.ok(service.updateCompanyInfo(updatedInfo));
	}

	@PostMapping("/{id}/delete")
	public String deleteCompany(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		service.deleteCompany(id);
		redirectAttributes.addFlashAttribute("message", "会社情報を削除しました。");
		return "redirect:/admin/company";
	}
}