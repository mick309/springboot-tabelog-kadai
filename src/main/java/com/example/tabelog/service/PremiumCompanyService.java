package com.example.tabelog.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tabelog.entity.PremiumCompany;
import com.example.tabelog.repository.PremiumCompanyRepository;

@Service
public class PremiumCompanyService {

    private final PremiumCompanyRepository repository;

    public PremiumCompanyService(PremiumCompanyRepository repository) {
        this.repository = repository;
    }

    /**
     * 単一の会社情報を取得
     *
     * @return Optional<PremiumCompany>
     */
    public Optional<PremiumCompany> getSingleCompany() {
        // 1件目のデータを取得
        return repository.findAll().stream().findFirst();
    }

    /**
     * 会社情報を更新
     *
     * @param updatedInfo 更新する会社情報
     * @return 更新後の会社情報
     */
    public PremiumCompany updateCompanyInfo(PremiumCompany updatedInfo) {
        // 会社情報を保存
        return repository.save(updatedInfo);
    }
}
