package com.example.tabelog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

	// 下限価格で検索し、作成日時の降順で並べ替え
	// Correction on the method name
	Page<Shop> findByPriceLowerGreaterThanEqualOrderByCreatedAtDesc(Integer priceLower, Pageable pageable);

	// 下限価格で検索し、上限価格の昇順で並べ替え
	Page<Shop> findByPriceLowerGreaterThanEqualOrderByPriceUpperAsc(Integer priceUpper, Pageable pageable);

	// 全てのデータを作成日時の降順で取得
	Page<Shop> findAllByOrderByCreatedAtDesc(Pageable pageable);

	// 全てのデータを上限価格の昇順で取得
	Page<Shop> findAllByOrderByPriceUpperAsc(Pageable pageable);

	// トップ10のデータを作成日時の降順で取得
	List<Shop> findTop10ByOrderByCreatedAtDesc();

	Page<Shop> findByAddressLike(String area, Pageable pageable);

	Page<Shop> findByPriceUpperGreaterThanEqualOrderByPriceUpperAsc(Integer priceUpper, Pageable pageable);

	Page<Shop> findByPriceUpperGreaterThanEqualOrderByCreatedAtDesc(Integer priceUpper, Pageable pageable);

	Page<Shop> findAllByOrderByPriceUpperAsc(Integer priceUpper, Pageable pageable);

	Page<Shop> findByShopNameLikeOrAddressLikeOrderByPriceUpperAsc(String shopName, String address, Pageable pageable);

	Page<Shop> findByShopNameLikeOrAddressLikeOrderByCreatedAtDesc(String shopName, String address, Pageable pageable);

	Page<Shop> findByAddressLikeOrderByCreatedAtDesc(String address, Pageable pageable);

	Page<Shop> findByAddressLikeOrderByCreatedAtAsc(String address, Pageable pageable);
	
	Page<Shop> findByShopNameContainingOrAddressContaining(String shopNameKeyword, String addressKeyword, Pageable pageable);
}
