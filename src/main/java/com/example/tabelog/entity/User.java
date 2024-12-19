package com.example.tabelog.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 50)
	@Column(nullable = false)
	private String name;

	@NotBlank
	@Size(max = 50)
	private String furigana;

	@NotBlank
	@Size(max = 8) // 日本の郵便番号対応
	@Column(name = "postal_code")
	private String postalCode;

	@NotBlank
	@Size(max = 255)
	private String address;

	@NotBlank
	@Size(max = 15)
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotBlank
	@Email
	@Size(max = 255)
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank
	@Size(min = 8, max = 255)
	@Column(nullable = false)
	private String password;

	private Boolean enabled = true;

	@Column(name = "is_premium", nullable = false)
	private Boolean isPremium = false;

	@Column(name = "is_paid", nullable = false)
	private Boolean isPaid = false;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	@Column(name = "customer_id")
	private String customerId;

	// 明確なゲッター/セッター（必要に応じて追加）
	public Boolean getIsPremium() {
		return isPremium;
	}

	public void setIsPremium(Boolean isPremium) {
		this.isPremium = isPremium;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
		
		
	}
}
