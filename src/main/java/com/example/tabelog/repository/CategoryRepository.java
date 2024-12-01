package com.example.tabelog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.Category; // ここを確認

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
