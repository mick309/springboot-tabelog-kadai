package com.example.tabelog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.GeneralUser;

public interface GeneralUserRepository extends JpaRepository<GeneralUser, Long> {
	Optional<GeneralUser> findByEmail(String email);
}