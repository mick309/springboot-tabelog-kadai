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

	@GetMapping
	public String showCategories(Model model) {
		List<Category> categories = categoryService.findAll();
		if (categories.isEmpty()) {
			categories = List.of(new Category(0, "ダミーカテゴリー")); // 仮のデータ
		}
		model.addAttribute("categories", categories);
		return "admin/categories/index";
	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("category", new Category());
		return "admin/categories/register";
	}

	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable Integer id, Model model) {
		Category category = categoryService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
		model.addAttribute("category", category);
		return "admin/categories/edit";
	}

	@PostMapping
	public String createCategory(@ModelAttribute Category category) {
		categoryService.save(category);
		return "redirect:/admin/categories";
	}

	@PostMapping("/{id}/edit")
	public String updateCategory(@PathVariable Integer id, @ModelAttribute Category category) {
		category.setId(id);
		categoryService.save(category);
		return "redirect:/admin/categories";
	}

	@PostMapping("/{id}/delete")
	public String deleteCategory(@PathVariable Integer id) {
		categoryService.deleteById(id);
		return "redirect:/admin/categories";
	}

}
