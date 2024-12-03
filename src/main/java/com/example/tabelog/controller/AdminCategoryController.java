package com.example.tabelog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tabelog.entity.Category;
import com.example.tabelog.service.CategoryService;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;

	// カテゴリー一覧表示
	@GetMapping
	public String showCategories(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "admin/categories/index"; // categories/index.htmlにマッピング
	}

	// 新規登録フォーム
	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("category", new Category());
		return "admin/categories/register";
	}

	// 編集フォーム
	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Integer id, Model model) {
		// Optional<Category> を返すので、orElseThrowで値を取り出す
		Category category = categoryService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
		model.addAttribute("category", category);
		return "admin/categories/edit"; // edit.htmlを返す
	}

	// 登録処理
	@PostMapping
	public String createCategory(@ModelAttribute Category category) {
		categoryService.save(category);
		return "redirect:/admin/categories";
	}

	// 更新処理
	@PostMapping("/{id}/edit")
	public String updateCategory(@PathVariable Integer id, @ModelAttribute Category category) {
		category.setId(id); // IDをセットして更新
		categoryService.save(category); // 更新処理
		return "redirect:/admin/categories"; // 更新後、一覧ページへリダイレクト
	}

	// 削除処理
	@PostMapping("/{id}/delete")
	public String deleteCategory(@PathVariable Integer id) {
		categoryService.deleteById(id);
		return "redirect:/admin/categories"; // カテゴリー一覧ページにリダイレクト
	}
}
