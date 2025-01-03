package com.example.tabelog.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tabelog.entity.GeneralUser;
import com.example.tabelog.repository.GeneralUserRepository;

@Service
public class GeneralUserService {

	private final GeneralUserRepository generalUserRepository;

	public GeneralUserService(GeneralUserRepository generalUserRepository) {
		this.generalUserRepository = generalUserRepository;
	}

	public Optional<GeneralUser> findById(Long id) {
		return generalUserRepository.findById(id);
	}

	public GeneralUser save(GeneralUser generalUser) {
		return generalUserRepository.save(generalUser);
	}

	public void deleteById(Long id) {
		generalUserRepository.deleteById(id);
	}

	public GeneralUser getCurrentUser() {
		// ログインしているユーザーの取得ロジックを実装
		// 仮実装: 本番環境では SecurityContextHolder などを使う
		throw new UnsupportedOperationException("未実装");
	}

	public void updateCurrentUser(GeneralUser user) {
		// 更新ロジックを実装
		throw new UnsupportedOperationException("未実装");
	}

	public void deleteCurrentUser() {
		// 現在のユーザー削除ロジックを実装
		throw new UnsupportedOperationException("未実装");
	}
}
