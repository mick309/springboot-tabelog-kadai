package com.example.tabelog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.tabelog.entity.Role;
import com.example.tabelog.repository.RoleRepository;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public List<Role> findAll() {
		return roleRepository.findAll(); // 全ロールを取得
	}
}
