package com.example.tabelog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.tabelog.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

	// 新着ショップ（トップ10）
	List<Shop> findTop10ByOrderByCreatedAtDesc();

	// キーワード検索（店舗名 or 住所）作成日時降順
	Page<Shop> findByShopNameLikeOrAddressLikeOrderByCreatedAtDesc(String shopName, String address, Pageable pageable);

	// 住所検索 作成日時降順
	Page<Shop> findByAddressLikeOrderByCreatedAtDesc(String address, Pageable pageable);

	// 下限価格以上 作成日時降順
	Page<Shop> findByPriceLowerGreaterThanEqualOrderByCreatedAtDesc(Integer priceLower, Pageable pageable);

	// 下限価格以上 上限価格昇順
	Page<Shop> findByPriceLowerGreaterThanEqualOrderByPriceUpperAsc(Integer priceLower, Pageable pageable);

	// 全件 作成日時降順
	Page<Shop> findAllByOrderByCreatedAtDesc(Pageable pageable);

	// 全件 上限価格昇順
	Page<Shop> findAllByOrderByPriceUpperAsc(Pageable pageable);

	// キーワード検索（店舗名または住所を部分一致検索）
	@Query("SELECT s FROM Shop s WHERE s.shopName LIKE %:keyword% OR s.address LIKE %:keyword%")
	Page<Shop> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
