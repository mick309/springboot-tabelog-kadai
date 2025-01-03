package com.example.tabelog.entity;

import java.util.Set;
<<<<<<< HEAD
import java.util.stream.Collectors;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@EqualsAndHashCode(of = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50文字以内で入力してください")
	@Column(nullable = false)
	private String name;

	@NotBlank(message = "フリガナを入力してください")
	@Size(max = 50, message = "フリガナは50文字以内で入力してください")
	private String furigana;

	@NotBlank(message = "郵便番号を入力してください")
	@Size(max = 8, message = "郵便番号は8文字以内で入力してください")
	@Column(name = "postal_code")
	private String postalCode;

	@NotBlank(message = "住所を入力してください")
	@Size(max = 255, message = "住所は255文字以内で入力してください")
	private String address;

	@NotBlank(message = "電話番号を入力してください")
	@Size(max = 15, message = "電話番号は15文字以内で入力してください")
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "有効なメールアドレスを入力してください")
	@Size(max = 255, message = "メールアドレスは255文字以内で入力してください")
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 8, max = 255, message = "パスワードは8文字以上255文字以内で入力してください")
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean enabled = true;

	@Column(name = "is_premium", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean isPremium = false;

	@Column(name = "is_paid", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean isPaid = false;

	@Column(name = "customer_id")
	private String customerId;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles; // 複数のRoleに対応

	public Set<Role> getRoles() {
		return roles;
	}

	public String getRoleNames() {
		return roles.stream()
				.map(Role::getName)
				.collect(Collectors.joining(", "));
	}

	// --- カスタムメソッド ---

	public void enablePremium() {
		this.isPremium = true;
		this.isPaid = true;
	}

	public void disablePremium() {
		this.isPremium = false;
		this.isPaid = false;
	}

	public Boolean isPremiumUser() {
		return Boolean.TRUE.equals(this.isPremium);
	}

	public Boolean isPaymentComplete() {
		return Boolean.TRUE.equals(this.isPaid);
	}

=======

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@EqualsAndHashCode(of = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "名前を入力してください")
	@Size(max = 50, message = "名前は50文字以内で入力してください")
	@Column(nullable = false)
	private String name;

	@NotBlank(message = "フリガナを入力してください")
	@Size(max = 50, message = "フリガナは50文字以内で入力してください")
	private String furigana;

	@NotBlank(message = "郵便番号を入力してください")
	@Size(max = 8, message = "郵便番号は8文字以内で入力してください")
	@Column(name = "postal_code")
	private String postalCode;

	@NotBlank(message = "住所を入力してください")
	@Size(max = 255, message = "住所は255文字以内で入力してください")
	private String address;

	@NotBlank(message = "電話番号を入力してください")
	@Size(max = 15, message = "電話番号は15文字以内で入力してください")
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "有効なメールアドレスを入力してください")
	@Size(max = 255, message = "メールアドレスは255文字以内で入力してください")
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank(message = "パスワードを入力してください")
	@Size(min = 8, max = 255, message = "パスワードは8文字以上255文字以内で入力してください")
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private Boolean enabled = true;

	@Column(name = "is_premium", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean isPremium = false;

	@Column(name = "is_paid", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private Boolean isPaid = false;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;//サブロール

	@Column(name = "customer_id")
	private String customerId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role; // メインロール

		// カスタムメソッド
	public void enablePremium() {
		this.isPremium = true;
		this.isPaid = true;
	}

	public void disablePremium() {
		this.isPremium = false;
		this.isPaid = false;
	}

	public Boolean isPremiumUser() {
		return Boolean.TRUE.equals(this.isPremium);
	}

	public Boolean isPaymentComplete() {
		return Boolean.TRUE.equals(this.isPaid);
	}

	// ユーザーのロールを返す
	public Set<Role> getRoles() {
		return roles;
	}
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
}
