package com.example.tabelog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

	// 下限価格で検索し、作成日時の降順で並べ替え
	Page<Shop> findByPriceLowerLessThanEqualOrderByCreatedAtDesc(Integer priceLower, Pageable pageable);

	// 下限価格で検索し、上限価格の昇順で並べ替え
	Page<Shop> findByPriceLowerLessThanEqualOrderByPriceUpperAsc(Integer priceUpper, Pageable pageable);

	// 全てのデータを作成日時の降順で取得
	Page<Shop> findAllByOrderByCreatedAtDesc(Pageable pageable);

	// 全てのデータを上限価格の昇順で取得
	Page<Shop> findAllByOrderByPriceUpperAsc(Pageable pageable);

	// トップ10のデータを作成日時の降順で取得
	List<Shop> findTop10ByOrderByCreatedAtDesc();

	Page<Shop> findByAddressLike(String area, Pageable pageable);

	Page<Shop> findByPriceLessThanEqual(Integer priceLower, Pageable pageable);

	Page<Shop> findByPriceLessThanEqualOrderByPriceAsc(Integer priceUpper, Pageable pageable);

	Page<Shop> findByPriceLessThanEqualOrderByCreatedAtDesc(Integer priceLower, Pageable pageable);

	Page<Shop> findAllByOrderByPriceAsc(Integer priceUpper, Pageable pageable);

	Page<Shop> findByShopNameLikeOrAddressLikeOrderByPriceUpperAsc(String shopName, String address, Pageable pageable);

	Page<Shop> findByShopNameLikeOrAddressLikeOrderByCreatedAtDesc(String shopName, String address, Pageable pageable);

	Page<Shop> findByAddressLikeOrderByPriceAsc(String shopName, String address, Pageable pageable);

	Page<Shop> findByAddressLikeOrderByCreatedAtDesc(String shopName, String address, Pageable pageable);

	Page<Shop> findByAddressLikeOrderByCreatedAtAsc(String shopName, String address, Pageable pageable);

}
