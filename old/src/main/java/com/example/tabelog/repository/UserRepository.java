package com.example.tabelog.repository;

<<<<<<< HEAD
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findById(Integer id);

	Optional<User> findByEmail(String email); // Optional を返すように修正

	public Page<User> findByNameLikeOrFuriganaLike(String nameKeyword, String furiganaKeyword, Pageable pageable);
	
	boolean existsByEmail(String email);

	Optional<User> findByEmailIgnoreCase(String email);
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByEmail(String email);

	public Page<User> findByNameLikeOrFuriganaLike(String nameKeyword, String furiganaKeyword, Pageable pageable);
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git

}
