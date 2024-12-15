package com.example.tabelog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String index(Pageable pageable, Model model) {
		Page<Review> reviewPage = reviewRepository.findAll(pageable);
		model.addAttribute("reviewPage", reviewPage);
		return "admin/review/index";
	}
}
