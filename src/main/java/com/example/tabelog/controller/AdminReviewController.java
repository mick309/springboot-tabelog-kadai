package com.example.tabelog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tabelog.entity.Review;
import com.example.tabelog.repository.ReviewRepository;

@Controller
@RequestMapping("/admin/review")
public class AdminReviewController {

	private final ReviewRepository reviewRepository;

	public AdminReviewController(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@GetMapping
	public String index(@PageableDefault(size = 10) Pageable pageable, Model model) {
		Page<Review> reviewPage = reviewRepository.findAll(pageable);
		model.addAttribute("reviewPage", reviewPage);
		return "admin/review/index";
	}

	// レビュー削除機能
	@PostMapping("/{reviewId}/delete")
	public String delete(@PathVariable Long reviewId, RedirectAttributes redirectAttributes) {
		reviewRepository.deleteById(reviewId);
		redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");
		return "redirect:/admin/review";
	}

	
}
