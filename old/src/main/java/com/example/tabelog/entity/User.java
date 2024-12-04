package com.example.tabelog.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
<<<<<<< HEAD

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id; // User ID（主キー）

	@Column(name = "name")
	private String name; // ユーザー名

	@Column(name = "furigana")
	private String furigana; // フリガナ

	@Column(name = "postal_code")
	private String postalCode; // 郵便番号

	@Column(name = "address")
	private String address; // 住所

	@Column(name = "phone_number")
	private String phoneNumber; // 電話番号

	@Column(name = "email")
	private String email; // メールアドレス

	@Column(name = "password")
	private String password; // パスワード

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@Column(nullable = false)
	private Boolean enabled = true; // ユーザーが有効かどうか（デフォルト: true）

	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt; // 作成日時（データベースによって自動設定）

	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt; // 更新日時（データベースによって自動設定）
=======
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "furigana")
	private String furigana;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt;
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
}
