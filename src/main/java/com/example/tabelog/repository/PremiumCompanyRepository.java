package com.example.tabelog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.PremiumCompany;

public interface PremiumCompanyRepository extends JpaRepository<PremiumCompany, Long> {
    Optional<PremiumCompany> findFirstByOrderByIdAsc();
}
