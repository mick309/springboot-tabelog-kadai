package com.example.tabelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        model.addAttribute("menu", new String[]{"会員一覧", "店舗一覧", "カテゴリー一覧", "会社情報", "ログアウト"});
        return "admin";
    }
}
