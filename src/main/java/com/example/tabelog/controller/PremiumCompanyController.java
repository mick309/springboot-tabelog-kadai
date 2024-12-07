package com.example.tabelog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tabelog.entity.PremiumCompany;
import com.example.tabelog.service.PremiumCompanyService;

@RestController
@RequestMapping("/premium/company")
public class PremiumCompanyController {

    private final PremiumCompanyService service;

    public PremiumCompanyController(PremiumCompanyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PremiumCompany> getPremiumCompanyInfo() {
        return service.getSingleCompany()
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("プレミアム会社情報が存在しません"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PremiumCompany> updatePremiumCompanyInfo(@PathVariable Long id, @RequestBody PremiumCompany updatedInfo) {
        return ResponseEntity.ok(service.updateCompanyInfo(updatedInfo));
    }
}
