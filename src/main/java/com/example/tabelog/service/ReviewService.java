package com.example.tabelog.service;

import org.springframework.stereotype.Service;

import com.example.tabelog.entity.Review;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.ReviewEditForm;
import com.example.tabelog.form.ReviewRegisterForm;
import com.example.tabelog.repository.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {

	private ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	@Transactional
	public void create(ReviewRegisterForm form, Shop shop, User user) {
		Review review = new Review();
		review.setEvaluation(form.getEvaluation());
		review.setReview_comment(form.getReview_comment());
		review.setShop(shop);
		review.setUser(user);
		reviewRepository.save(review);
	}

	
	public boolean isReviewDone(User user, Shop shop) {
		return reviewRepository.findByShopAndUser(shop, user) != null;

	}

	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
		Review review = reviewRepository.getReferenceById(reviewEditForm.getId());

		review.setEvaluation(reviewEditForm.getEvaluation());
		review.setReview_comment(reviewEditForm.getReview_comment());

		reviewRepository.save(review);

	}

}
