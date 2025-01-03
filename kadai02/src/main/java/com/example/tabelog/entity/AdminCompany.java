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
<<<<<<< HEAD
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "company")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class AdminCompany {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 主キー

	@Column(name = "company_name", nullable = false)
	private String companyName; // 会社名

	@Column(name = "address")
	private String address; // 住所

	@Column(name = "phone_number")
	private String phoneNumber; // 電話番号

	@Column(name = "contact_email")
	private String contactEmail; // メールアドレス

	@Column(name = "website_url")
	private String websiteUrl; // ウェブサイトURL

	@Column(name = "services")
	private String services; // サービス情報

	@Column(name = "portfolio_url")
	private String portfolioUrl; // ポートフォリオURL

	@Column(name = "established_year")
	private Integer establishedYear; // 設立年

	@Column(name = "technology_stack")
	private String technologyStack; // 使用技術のスタック

	@Column(name = "description")
	private String description; // 会社概要

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
=======
import lombok.Data;

@Entity
@Table(name = "company")
@Data
public class AdminCompany {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 主キー

	@Column(name = "company_name", nullable = false) // データベースカラム名に合わせる
	private String companyName; // 会社名

	@Column(name = "address") // 必要に応じて明示的に指定
	private String address; // 住所

	@Column(name = "phone_number") // データベースカラム名に合わせる
	private String phoneNumber; // 電話番号

	@Column(name = "contact_email") // データベースカラム名に合わせる
	private String contactEmail; // メールアドレス

	@Column(name = "website_url") // データベースカラム名に合わせる
	private String websiteUrl; // ウェブサイトURL

	@Column(name = "services") // 明示的に指定
	private String services; // サービス情報

	@Column(name = "portfolio_url") // データベースカラム名に合わせる
	private String portfolioUrl; // ポートフォリオURL

	@Column(name = "established_year") // データベースカラム名に合わせる
	private Integer establishedYear; // 設立年

	@Column(name = "technology_stack") // データベースカラム名に合わせる
	private String technologyStack; // 使用技術のスタック

	@Column(name = "description") // 明示的に指定
	private String description; // 会社概要

	@Column(name = "created_at", updatable = false) // 作成日時
	private LocalDateTime createdAt;

	@Column(name = "updated_at") // 更新日時
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
	private LocalDateTime updatedAt;

	// 作成前にタイムスタンプを設定
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	// 更新前にタイムスタンプを設定
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
}
