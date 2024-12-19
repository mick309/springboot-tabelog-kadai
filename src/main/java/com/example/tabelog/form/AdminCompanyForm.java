package com.example.tabelog.form;

import com.example.tabelog.entity.AdminCompany;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminCompanyForm {

	private Long id;

	@NotBlank(message = "会社名は必須です")
	@Size(max = 255, message = "会社名は255文字以内で入力してください")
	private String companyName;

	@Size(max = 255, message = "住所は255文字以内で入力してください")
	private String address;

	@Size(max = 15, message = "電話番号は15文字以内で入力してください")
	private String phoneNumber;

	@Email(message = "正しいメールアドレスを入力してください")
	@NotBlank(message = "メールアドレスは必須です")
	private String contactEmail;

	private String websiteUrl; // ウェブサイトURL
	private String services; // サービス情報
	private String portfolioUrl; // ポートフォリオURL
	private String description; // 会社概要
	private String technologyStack; // 使用技術スタック
	private Integer establishedYear; // 設立年

	// エンティティからフォームへの変換
	public static AdminCompanyForm fromEntity(AdminCompany company) {
		AdminCompanyForm form = new AdminCompanyForm();
		form.setId(company.getId());
		form.setCompanyName(company.getCompanyName());
		form.setAddress(company.getAddress());
		form.setPhoneNumber(company.getPhoneNumber());
		form.setContactEmail(company.getContactEmail());
		form.setWebsiteUrl(company.getWebsiteUrl());
		form.setServices(company.getServices());
		form.setPortfolioUrl(company.getPortfolioUrl());
		form.setDescription(company.getDescription());
		form.setTechnologyStack(company.getTechnologyStack());
		form.setEstablishedYear(company.getEstablishedYear());
		return form;
	}

	// フォームからエンティティへの変換
	public AdminCompany toEntity() {
		AdminCompany company = new AdminCompany();
		company.setId(this.id);
		company.setCompanyName(this.companyName);
		company.setAddress(this.address);
		company.setPhoneNumber(this.phoneNumber);
		company.setContactEmail(this.contactEmail);
		company.setWebsiteUrl(this.websiteUrl);
		company.setServices(this.services);
		company.setPortfolioUrl(this.portfolioUrl);
		company.setDescription(this.description);
		company.setTechnologyStack(this.technologyStack);
		company.setEstablishedYear(this.establishedYear);
		return company;
	}
}
