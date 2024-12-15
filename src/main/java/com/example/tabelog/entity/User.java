package com.example.tabelog.entity;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String furigana;

	@Column(nullable = false)
	private String postalCode;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String phoneNumber;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	private Boolean enabled;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	@Column(unique = true)
	private String customerId; // Stripe顧客IDを保存

	private Boolean premium; // プレミアム会員フラグ

	@Column(nullable = false)
	private Boolean isPaid; // 課金済みステータス

	// Lombokの@Dataアノテーションによりセッターとゲッターが自動生成される
}
