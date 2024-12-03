package com.example.tabelog.service;

import java.util.List;
import java.util.Optional; // Optional をインポート

import org.springframework.stereotype.Service;

import com.example.tabelog.entity.Category;
import com.example.tabelog.repository.CategoryRepository;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	// IDでカテゴリを取得（Optionalを返す）
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id); // Optionalを返す
	}

	// すべてのカテゴリを取得
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	// カテゴリを保存（新規登録・更新）
	public void save(Category category) {
		categoryRepository.save(category); // CategoryRepositoryのsaveメソッドを呼び出して保存
	}

	// IDでカテゴリを削除（Integer型の引数を受け取る）
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id); // CategoryRepositoryのdeleteByIdメソッドを呼び出して削除
	}

	// getCategoryById メソッドの追加
	public Category getCategoryById(Integer id) {
		return categoryRepository.findById(id).orElse(null);
	}

}
