package com.example.tabelog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.tabelog.entity.Role;
import com.example.tabelog.repository.RoleRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		// 役割が存在しない場合のみ挿入
		if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
			Role adminRole = new Role();
			adminRole.setName("ROLE_ADMIN");
			roleRepository.save(adminRole);
			System.out.println("ROLE_ADMIN を追加しました。");
		}

		if (roleRepository.findByName("ROLE_GENERAL").isEmpty()) {
			Role generalRole = new Role();
			generalRole.setName("ROLE_GENERAL");
			roleRepository.save(generalRole);
			System.out.println("ROLE_GENERAL を追加しました。");
		}

		if (roleRepository.findByName("ROLE_PREMIUM_USER").isEmpty()) {
			Role premiumRole = new Role();
			premiumRole.setName("ROLE_PREMIUM_USER");
			roleRepository.save(premiumRole);
			System.out.println("ROLE_PREMIUM_USER を追加しました。");
		}
	}
}
