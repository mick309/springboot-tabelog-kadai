package com.example.tabelog.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 主キー

	private String name;
	private String furigana;
	private String postalCode;
	private String address;
	private String phoneNumber;
	private String email;
	private String password;
	private Boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", // 中間テーブル名
			joinColumns = @JoinColumn(name = "user_id"), // Userの外部キー
			inverseJoinColumns = @JoinColumn(name = "role_id") // Roleの外部キー
	)
	private Set<Role> roles = new HashSet<>();

	// カスタムメソッド
	public Set<String> getRoleNames() {
		return roles.stream()
				.map(Role::getName)
				.collect(Collectors.toSet());
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}
}
