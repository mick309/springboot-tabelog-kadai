package com.example.tabelog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.AdminCompany;

public interface AdminCompanyRepository extends JpaRepository<AdminCompany, Long> {
	Optional<AdminCompany> findFirstByOrderById();
}
