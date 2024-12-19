package com.example.tabelog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.Review;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.entity.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	public Review findByShopAndUser(Shop Shop, User user);

	public Page<Review> findByShopOrderByCreatedAtDesc(Shop Shop, Pageable pageable);

	public List<Review> findTop6ByShopOrderByCreatedAtDesc(Shop Shop);

	public long countByShop(Shop Shop);

	public List<Review> findByShopOrderByCreatedAtDesc(Shop shop);

	List<Review> findByUser(User user);

	Optional<Review> findByIdAndUser(Integer id, User user);

	List<Review> findByShopId(Integer shopId);

}
