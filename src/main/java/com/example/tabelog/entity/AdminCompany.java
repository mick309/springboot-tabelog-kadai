package com.example.tabelog.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "company")
@Data
public class AdminCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主キー

    @Column(nullable = false)
    private String companyName; // 会社名

    private String address; // 住所
    private String phoneNumber; // 電話番号
    private String contactEmail; // メールアドレス
    private String websiteUrl; // ウェブサイトURL
    private String services; // サービス情報
    private String portfolioUrl; // ポートフォリオURL
    private Integer establishedYear; // 設立年
    private String technologyStack; // 使用技術のスタック
    private String description; // 会社概要

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 作成日時

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 更新日時

    // 作成前にタイムスタンプを設定
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 更新前にタイムスタンプを設定
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}