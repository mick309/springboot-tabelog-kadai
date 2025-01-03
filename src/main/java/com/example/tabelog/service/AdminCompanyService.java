package com.example.tabelog.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.tabelog.entity.AdminCompany;
import com.example.tabelog.repository.AdminCompanyRepository;

@Service
public class AdminCompanyService {

	private static final Logger logger = LoggerFactory.getLogger(AdminCompanyService.class);
	private final AdminCompanyRepository repository;

	public AdminCompanyService(AdminCompanyRepository repository) {
		this.repository = repository;
	}

	public Optional<AdminCompany> getSingleCompany() {
		return repository.findFirstByOrderById();
	}

	public AdminCompany updateCompanyInfo(AdminCompany company) {
		AdminCompany savedCompany = repository.save(company);
		logger.info("Updated company information: {}", savedCompany);
		return savedCompany;
	}
}