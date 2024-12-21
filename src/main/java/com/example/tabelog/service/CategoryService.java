package com.example.tabelog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.tabelog.entity.Category;
import com.example.tabelog.repository.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	// 📌 IDでカテゴリを取得（Optionalを返す）
	public Optional<Category> findById(Long id) {
		return categoryRepository.findById(id);
	}

	// 📌 すべてのカテゴリを取得
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	// 📌 カテゴリを保存（新規登録・更新）
	public void save(Category category) {
		categoryRepository.save(category);
	}

	// 📌 IDでカテゴリを削除（Long型の引数を受け取る）
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

	// 📌 IDでカテゴリを取得（存在しない場合はnullを返す）
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}
}
