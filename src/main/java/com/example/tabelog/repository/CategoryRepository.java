package com.example.tabelog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
