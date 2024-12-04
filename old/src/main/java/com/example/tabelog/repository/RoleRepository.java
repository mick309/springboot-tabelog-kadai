package com.example.tabelog.repository;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tabelog.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name); // これが正しい定義
}
=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	 public Role findByName(String name);    
}
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
