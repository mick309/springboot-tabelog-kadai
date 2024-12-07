package com.example.tabelog.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tabelog.entity.AdminCompany;
import com.example.tabelog.repository.AdminCompanyRepository;

@Service
public class AdminCompanyService {

	private final AdminCompanyRepository repository;

	public AdminCompanyService(AdminCompanyRepository repository) {
		this.repository = repository;
	}

	public Optional<AdminCompany> getSingleCompany() {
		return repository.findAll().stream().findFirst();
	}

	public AdminCompany updateCompanyInfo(AdminCompany company) {
		AdminCompany savedCompany = repository.save(company);
		System.out.println("Saved company: " + savedCompany);
		return savedCompany;
	}

}