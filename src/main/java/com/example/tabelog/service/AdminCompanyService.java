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

    // 新規追加: 会社情報を1件取得
    public Optional<AdminCompany> getSingleCompany() {
        // データベースの最初の1件を取得する例
        return repository.findAll().stream().findFirst();
    }

    // 他の既存メソッド
    public Optional<AdminCompany> getCompanyInfo(Long id) {
        return repository.findById(id);
    }

    public AdminCompany updateCompanyInfo(AdminCompany company) {
        return repository.save(company);
    }

    public void deleteCompany(Long id) {
        repository.deleteById(id);
    }
}
