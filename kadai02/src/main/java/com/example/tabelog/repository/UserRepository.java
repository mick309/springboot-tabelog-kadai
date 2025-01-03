package com.example.tabelog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // メールアドレスでユーザーを検索
    Optional<User> findByEmailIgnoreCase(String email);

    // 名前またはフリガナで検索
    Page<User> findByNameLikeOrFuriganaLike(String nameKeyword, String furiganaKeyword, Pageable pageable);

    // メールアドレスの存在確認
    boolean existsByEmail(String email);

    // customerId でユーザーを検索 (課金済みユーザー用)
    Optional<User> findByCustomerId(String customerId);
    
    Optional<User> findByEmail(String email);
    
   
}