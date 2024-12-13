package com.example.tabelog.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "companies")
@Data
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 主キー

	@Column(name = "company_name", nullable = false)
	private String companyName;

	@Column(name = "website_url")
	private String websiteUrl;

	@Column(name = "contact_email")
	private String contactEmail;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "services")
	private String services;

	@Column(name = "portfolio_url")
	private String portfolioUrl;

	@Column(name = "established_year")
	private Integer establishedYear;

	@Column(name = "technology_stack")
	private String technologyStack;

	@Column(name = "description")
	private String description;

	@Column(name = "premium_info")
	private String premiumInfo; // プレミアム情報を保持するフィールド

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	// プレミアム情報を設定するメソッド
	public void setPremiumInfo(String premiumInfo) {
		this.premiumInfo = premiumInfo;
	}

	// 作成日時と更新日時を自動設定するためのメソッド
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
