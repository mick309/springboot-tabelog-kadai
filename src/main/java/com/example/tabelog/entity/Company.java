package com.example.tabelog.entity;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class Company {
	private Long id;
	private String companyName;
	private String websiteUrl;
	private String contactEmail;
	private String phoneNumber;
	private String address;
	private String services;
	private String portfolioUrl;
	private Integer establishedYear;
	private String technologyStack;
	private String description;
	private String premiumInfo; // プレミアム情報を保持するフィールド
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// プレミアム情報を設定するメソッド
	public void setPremiumInfo(String premiumInfo) {
		this.premiumInfo = premiumInfo;
	}
}