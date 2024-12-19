package com.example.tabelog.controller;

import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tabelog.entity.User;
import com.example.tabelog.repository.UserRepository;

@RestController
public class CompanyController {

	private final UserRepository userRepository;

	public CompanyController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// ユーザー情報とロールを取得する例
	@GetMapping("/user-roles")
	public String getUserRoles(@RequestParam Long userId) {
		// ユーザーを取得
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

		// ユーザーのロールを取得
		String roles = user.getRoles().stream()
				.map(role -> role.getName()) // Role エンティティの名前を取得
				.collect(Collectors.joining(", ")); // カンマ区切りにする

		return "User roles: " + roles;
	}
}
