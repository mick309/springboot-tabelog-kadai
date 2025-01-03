package com.example.tabelog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tabelog.entity.Review;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.entity.User;
import com.example.tabelog.form.ReviewEditForm;
import com.example.tabelog.form.ReviewRegisterForm;
import com.example.tabelog.repository.ReviewRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	// レビュー新規作成
	@Transactional
	public void create(ReviewRegisterForm form, Shop shop, User user) {
		Review review = new Review();
		review.setEvaluation(form.getEvaluation());
		review.setReviewComment(form.getReviewComment()); // 修正済みのgetterを使用
		review.setShop(shop);
		review.setUser(user);
		reviewRepository.save(review);
	}

	// レビューの存在確認（ユーザーと店舗の組み合わせ）
	public boolean isReviewDone(User user, Shop shop) {
		return reviewRepository.findByShopAndUser(shop, user) != null;
	}

	// レビューの更新
	@Transactional
	public void update(ReviewEditForm reviewEditForm) {
		Review review = reviewRepository.findById(reviewEditForm.getId())
				.orElseThrow(() -> new IllegalArgumentException("Review not found with ID: " + reviewEditForm.getId()));

		review.setEvaluation(reviewEditForm.getEvaluation());
		review.setReviewComment(reviewEditForm.getReviewComment()); // 正しいメソッド名を使用

		reviewRepository.save(review);
	}

	// 現在のユーザーの全レビューを取得
	public List<Review> findReviewsByCurrentUser(User user) {
		return reviewRepository.findByUser(user);
	}

	// 指定されたレビューを取得 (ユーザーによる制限付き)
	public Review findByIdAndCurrentUser(Integer id, User user) {
		return reviewRepository.findByIdAndUser(id, user)
				.orElseThrow(() -> new EntityNotFoundException("レビューが見つかりません: ID " + id));
	}

	// レビュー削除（現在のユーザー）
	@Transactional
	public void deleteReviewByCurrentUser(Integer id, User user) {
		Review review = reviewRepository.findByIdAndUser(id, user)
				.orElseThrow(() -> new EntityNotFoundException("レビューが見つかりません: ID " + id));
		reviewRepository.delete(review);
	}

	// 店舗IDでレビューを取得
	public List<Review> findByShopId(Integer shopId) {
		return reviewRepository.findByShopId(shopId);
	}

	@Transactional
	public void updateReview(Integer id, User user, ReviewEditForm reviewEditForm) {
	    Review review = findByIdAndCurrentUser(id, user);
	    review.setEvaluation(reviewEditForm.getEvaluation());
	    review.setReviewComment(reviewEditForm.getReviewComment());
	    reviewRepository.save(review);
	}

}
