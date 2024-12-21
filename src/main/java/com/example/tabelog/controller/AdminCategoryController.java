package com.example.tabelog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.Category;
import com.example.tabelog.service.CategoryService;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // 📌 カテゴリ一覧ページ
    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/categories/index";
    }

    // 📌 カテゴリ登録ページ
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/categories/register";
    }

    // 📌 カテゴリ登録処理
    @PostMapping("/register")
    public String registerCategory(@ModelAttribute @Validated Category category,
                                   BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/categories/register";
        }

        categoryService.save(category);
        redirectAttributes.addFlashAttribute("successMessage", "カテゴリを登録しました。");
        return "redirect:/admin/categories";
    }

    // 📌 カテゴリ編集ページ
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("無効なカテゴリID: " + id));
        model.addAttribute("category", category);
        return "admin/categories/edit";
    }

    // 📌 カテゴリ更新処理
    @PostMapping("/{id}/update")
    public String updateCategory(@PathVariable("id") Long id,
                                 @ModelAttribute @Validated Category category,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "admin/categories/edit";
        }

        // 明示的にIDを設定
        category.setId(id);

        categoryService.save(category);
        redirectAttributes.addFlashAttribute("successMessage", "カテゴリを更新しました。");
        return "redirect:/admin/categories";
    }

    // 📌 カテゴリ削除処理
    @PostMapping("/{id}/delete")
    public String deleteCategory(@PathVariable("id") Long id,
                                 RedirectAttributes redirectAttributes) {
        categoryService.deleteById(id); // `delete`ではなく`deleteById`を使用
        redirectAttributes.addFlashAttribute("successMessage", "カテゴリを削除しました。");
        return "redirect:/admin/categories";
    }
}
