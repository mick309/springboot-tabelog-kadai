package com.example.tabelog.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

import com.example.tabelog.entity.Review;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.ReviewEditForm;
import com.example.tabelog.security.UserDetailsImpl;
import com.example.tabelog.service.ReviewService;
import com.example.tabelog.service.ShopService;

@Controller
@RequestMapping("/premium")
@PreAuthorize("hasRole('ROLE_PREMIUM_USER')")
public class PremiumUserController {

	private final ShopService shopService;
	private final ReviewService reviewService;

	public PremiumUserController(ShopService shopService, ReviewService reviewService) {
		this.shopService = shopService;
		this.reviewService = reviewService;
	}

	// 店舗詳細画面 (課金ユーザーと管理者のみ閲覧可能)
	@GetMapping("/shops/{id}")
	public String shopDetail(@PathVariable Integer id, Model model) {
		model.addAttribute("shop", shopService.findById(id));
		return "premium/shops/detail";
	}

	// 自分のレビュー一覧
	@GetMapping("/reviews")
	public String myReviews(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
		User currentUser = userDetails.getUser();
		model.addAttribute("reviews", reviewService.findReviewsByCurrentUser(currentUser));
		return "premium/reviews/list";
	}

	// レビュー編集画面
	@GetMapping("/reviews/{id}/edit")
	public String editReview(@PathVariable Integer id, @AuthenticationPrincipal UserDetailsImpl userDetails,
			Model model) {
		User currentUser = userDetails.getUser();
		Review review = reviewService.findByIdAndCurrentUser(id, currentUser);

		// ReviewEditForm を作成してモデルに追加
		ReviewEditForm reviewEditForm = new ReviewEditForm(
				review.getId(),
				review.getEvaluation(),
				review.getReview_comment());
		model.addAttribute("reviewEditForm", reviewEditForm);
		return "premium/reviews/edit";
	}

	// レビュー更新処理
	@PostMapping("/reviews/{id}/update")
	public String updateReview(@PathVariable Integer id,
			@AuthenticationPrincipal UserDetailsImpl userDetails,
			@ModelAttribute @Validated ReviewEditForm reviewEditForm,
			BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "premium/reviews/edit";
		}

		User currentUser = userDetails.getUser();
		reviewService.updateReview(id, currentUser, reviewEditForm);

		redirectAttributes.addFlashAttribute("successMessage", "レビューを更新しました。");
		return "redirect:/premium/reviews";
	}

	// レビュー削除
	@PostMapping("/reviews/{id}/delete")
	public String deleteReview(@PathVariable Integer id,
			@AuthenticationPrincipal UserDetailsImpl userDetails,
			RedirectAttributes redirectAttributes) {
		User currentUser = userDetails.getUser();
		reviewService.deleteReviewByCurrentUser(id, currentUser);
		redirectAttributes.addFlashAttribute("successMessage", "レビューを削除しました。");
		return "redirect:/premium/reviews";
	}
}
