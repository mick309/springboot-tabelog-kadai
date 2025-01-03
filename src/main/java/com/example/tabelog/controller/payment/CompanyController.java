package com.example.tabelog.controller.payment;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tabelog.entity.Company;
import com.example.tabelog.entity.User;
import com.example.tabelog.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/company")
public class CompanyController {

    private final UserRepository userRepository;

    public CompanyController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/view")
    public String viewCompanyInfo(Model model) {
        log.info("Accessing company information view");

        // サンプルデータを生成
        Company company = new Company();
        company.setCompanyName("サンプル株式会社");
        company.setAddress("東京都千代田区...");
        company.setPhoneNumber("03-1234-5678");
        company.setContactEmail("contact@sample.com");
        company.setEstablishedYear(1999);
        company.setWebsiteUrl("https://www.sample.com");
        company.setServices("飲食業、サービス業");
        company.setDescription("会社の概要について...");
        company.setPortfolioUrl("https://portfolio.sample.com");
        company.setTechnologyStack("Java, Spring, Thymeleaf");
        company.setPremiumInfo("プレミアム会員向けの詳細情報...");
        company.setId(1L);
        company.setCreatedAt(LocalDateTime.now());
        company.setUpdatedAt(LocalDateTime.now());

        model.addAttribute("company", company);
        return "company/view";
    }


    @GetMapping("/user-roles")
    @ResponseBody
    public String getUserRoles(@RequestParam Long userId) {
        log.info("Fetching roles for user ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        String roles = user.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.joining(", "));

        log.info("Roles for user ID {}: {}", userId, roles);
        return "User roles: " + roles;
    }
}