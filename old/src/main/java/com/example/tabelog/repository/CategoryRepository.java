package com.example.tabelog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import com.example.tabelog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
=======
import com.example.tabelog.entity.Category; // ここを確認

public interface CategoryRepository extends JpaRepository<Category, Long> {
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
}
