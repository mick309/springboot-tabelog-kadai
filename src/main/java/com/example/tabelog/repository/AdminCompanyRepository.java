package com.example.tabelog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.AdminCompany;

public interface AdminCompanyRepository extends JpaRepository<AdminCompany, Long> {
}
