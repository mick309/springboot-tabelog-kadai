package com.example.tabelog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tabelog.entity.Category;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.form.ShopEditForm;
import com.example.tabelog.repository.CategoryRepository;
import com.example.tabelog.repository.ShopRepository;

@Service
public class ShopService {

	private final ShopRepository shopRepository;
	private final CategoryRepository categoryRepository;

	public ShopService(ShopRepository shopRepository, CategoryRepository categoryRepository) {
		this.shopRepository = shopRepository;
		this.categoryRepository = categoryRepository;
	}

	// IDで店舗を取得
	public Shop findById(Long id) {
		return shopRepository.findById(id).orElse(null);
	}

	// すべての店舗を取得
	public List<Shop> getAllShops() {
		return shopRepository.findAll();
	}

	// ページネーション対応で店舗を取得
	public org.springframework.data.domain.Page<Shop> getAllShops(org.springframework.data.domain.Pageable pageable) {
		return shopRepository.findAll(pageable);
	}

	// キーワード検索
	public org.springframework.data.domain.Page<Shop> searchShopsByKeyword(String keyword,
			org.springframework.data.domain.Pageable pageable) {
		return shopRepository.searchByKeyword(keyword, pageable);
	}

	// 店舗を保存（新規登録・更新）
<<<<<<< HEAD
	@Transactional
	public void save(Shop shop) {
		if (!shopRepository.existsById(shop.getId())) {
			shopRepository.save(shop);
		} else {
			throw new IllegalArgumentException("同じIDの店舗が既に存在します。ID: " + shop.getId());
		}
	}

	// 店舗情報を更新 (ShopEditForm を受け取る)
	@Transactional
	public void update(ShopEditForm shopEditForm) {
		Shop shop = shopRepository.findById(shopEditForm.getId())
				.orElseThrow(() -> new IllegalArgumentException("指定されたIDの店舗が存在しません。ID: " + shopEditForm.getId()));

		// フォームの情報をShopエンティティに反映
		shop.setShopName(shopEditForm.getShopName());
		shop.setDescription(shopEditForm.getDescription());
		shop.setPriceUpper(shopEditForm.getPriceUpper());
		shop.setPriceLower(shopEditForm.getPriceLower());
		shop.setHoursOpen(shopEditForm.getHoursOpen());
		shop.setHoursClose(shopEditForm.getHoursClose());
		shop.setPostalCode(shopEditForm.getPostalCode());
		shop.setAddress(shopEditForm.getAddress());
		shop.setPhoneNumber(shopEditForm.getPhoneNumber());
		shop.setClosedDay(shopEditForm.getClosedDay());

		// カテゴリの設定 (存在確認)
		if (shopEditForm.getCategoryId() != null) {
			Category category = categoryRepository.findById(shopEditForm.getCategoryId())
					.orElseThrow(() -> new IllegalArgumentException("無効なカテゴリID: " + shopEditForm.getCategoryId()));
			shop.setCategory(category);
		}

		shopRepository.save(shop);
	}

	// 店舗を削除
	@Transactional
	public void delete(Long id) {
		if (shopRepository.existsById(id)) {
			shopRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("指定されたIDの店舗が存在しません。ID: " + id);
		}
	}

	// 📌 新着店舗（トップ10）を取得
	public List<Shop> findTop10ByOrderByCreatedAtDesc() {
		return shopRepository.findTop10ByOrderByCreatedAtDesc();
	}
}
=======
	public void save(Shop shop) {
		shopRepository.save(shop);
	}

	// 店舗情報を更新 (ShopEditForm を受け取る)
	@Transactional
	public void update(ShopEditForm shopEditForm) {
		Shop shop = shopRepository.findById(shopEditForm.getId())
				.orElseThrow(() -> new IllegalArgumentException("指定されたIDの店舗が存在しません。ID: " + shopEditForm.getId()));

		// フォームの情報をShopエンティティに反映
		shop.setShopName(shopEditForm.getShopName());
		shop.setDescription(shopEditForm.getDescription());
		shop.setPriceUpper(shopEditForm.getPriceUpper());
		shop.setPriceLower(shopEditForm.getPriceLower());
		shop.setHoursOpen(shopEditForm.getHoursOpen());
		shop.setHoursClose(shopEditForm.getHoursClose());
		shop.setPostalCode(shopEditForm.getPostalCode());
		shop.setAddress(shopEditForm.getAddress());
		shop.setPhoneNumber(shopEditForm.getPhoneNumber());
		shop.setClosedDay(shopEditForm.getClosedDay());

		// カテゴリの設定 (存在確認)
		if (shopEditForm.getCategoryId() != null) {
			Category category = categoryRepository.findById(shopEditForm.getCategoryId())
					.orElseThrow(() -> new IllegalArgumentException("無効なカテゴリID: " + shopEditForm.getCategoryId()));
			shop.setCategory(category);
		}

		shopRepository.save(shop);
	}

	// 店舗を削除
	public void delete(Long id) {
		if (shopRepository.existsById(id)) {
			shopRepository.deleteById(id);
		} else {
			throw new IllegalArgumentException("指定されたIDの店舗が存在しません。ID: " + id);
		}
	}
	
	// 📌 新着店舗（トップ10）を取得
	public List<Shop> findTop10ByOrderByCreatedAtDesc() {
	    return shopRepository.findTop10ByOrderByCreatedAtDesc();
	}
}
>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
