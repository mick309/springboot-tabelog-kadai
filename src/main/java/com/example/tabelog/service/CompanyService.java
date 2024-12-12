package com.example.tabelog.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.tabelog.entity.Company;

@Service
public class CompanyService {

	public Company getCompanyInfo() {
		// ダミーデータを作成（データベースから取得する場合は適切に実装）
		Company company = new Company();
		company.setId(1L);
		company.setCompanyName("Awesome App Studio");
		company.setWebsiteUrl("https://awesomeappstudio.com");
		company.setContactEmail("info@awesomeappstudio.com");
		company.setPhoneNumber("123-456-7890");
		company.setAddress("123 Main Street, Tokyo");
		company.setServices("Webアプリ開発, モバイルアプリ開発");
		company.setPortfolioUrl("https://portfolio.awesomeappstudio.com");
		company.setEstablishedYear(2010);
		company.setTechnologyStack("Java, Spring Boot, React, Node.js");
		company.setDescription("私たちは高品質なアプリ開発を提供します。");

		// 日時を LocalDateTime に変換
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		company.setCreatedAt(LocalDateTime.parse("2023-04-01 10:00:00", formatter));
		company.setUpdatedAt(LocalDateTime.parse("2023-04-01 10:00:00", formatter));

		return company;
	}
}
