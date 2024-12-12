package com.example.tabelog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tabelog.entity.User;
import com.example.tabelog.repository.UserRepository;

@Service
public class PasswordUpdateService {

	@Autowired
	private UserRepository userRepository; // UserRepositoryはあなたのエンティティに応じて作成

	public void updateAllPasswords() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		// 全ユーザーを取得
		List<User> users = userRepository.findAll();

		for (User user : users) {
			if (!user.getPassword().startsWith("$2a$")) { // すでにエンコード済みか確認
				String rawPassword = user.getPassword(); // 平文パスワード
				String encodedPassword = encoder.encode(rawPassword);
				user.setPassword(encodedPassword);
			}
		}

		// 一括保存
		userRepository.saveAll(users);
	}
}
