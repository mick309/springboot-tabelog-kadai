package com.example.tabelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tabelog.entity.AdminCompany;
import com.example.tabelog.service.AdminCompanyService;

@Controller
@RequestMapping("/user/company")
public class GeneralCompanyController {

    private final AdminCompanyService service;

    public GeneralCompanyController(AdminCompanyService service) {
        this.service = service;
    }

    /**
     * 一般ユーザー向けの会社情報ビューの表示
     */
    @GetMapping
    public String viewCompanyInfo(Model model) {
        // 一般ユーザー向け会社情報を取得（1件のみ想定）
        AdminCompany company = service.getSingleCompany()
                .orElseThrow(() -> new RuntimeException("会社情報が存在しません"));
        model.addAttribute("company", company);
        return "user/company/view"; // user用のview.htmlを表示
    }
}