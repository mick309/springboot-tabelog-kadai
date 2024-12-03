package com.example.tabelog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.tabelog.entity.Category;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.form.ShopEditForm;
import com.example.tabelog.form.ShopRegisterForm;
import com.example.tabelog.repository.ShopRepository;

import jakarta.persistence.Id;

@Service
public class ShopService {

	private final ShopRepository shopRepository;
	private final CategoryService categoryService;

	public ShopService(ShopRepository shopRepository, CategoryService categoryService) {
		this.shopRepository = shopRepository;
		this.categoryService = categoryService;
	}

	@Id
	private Integer id;

	@Transactional
	public void create(ShopRegisterForm shopRegisterForm) {
		Shop shop = new Shop();
		MultipartFile imageFile = shopRegisterForm.getImageFile();

		// カテゴリ設定
		Category category = categoryService.findById(shopRegisterForm.getCategoryId())
				.orElseThrow(
						() -> new IllegalArgumentException("Invalid category ID: " + shopRegisterForm.getCategoryId()));
		shop.setCategory(category);

		if (!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("path/to/your/static/storage/" + hashedImageName);
			copyImageFile(imageFile, filePath);
			shop.setImageName(hashedImageName);
		}

		// その他のプロパティ設定
		shop.setShopName(shopRegisterForm.getShopName());
		shop.setDescription(shopRegisterForm.getDescription());
		shop.setPriceUpper(shopRegisterForm.getPriceUpper());
		shop.setPriceLower(shopRegisterForm.getPriceLower());
		shop.setHoursOpen(shopRegisterForm.getHoursOpen());
		shop.setHoursClose(shopRegisterForm.getHoursClose());
		shop.setClosedDay(shopRegisterForm.getClosedDay());
		shop.setPostalCode(shopRegisterForm.getPostalCode());
		shop.setAddress(shopRegisterForm.getAddress());
		shop.setPhoneNumber(shopRegisterForm.getPhoneNumber());

		shopRepository.save(shop);
	}

	@Transactional
	public void update(ShopEditForm shopEditForm) {
		Shop shop = shopRepository.findById(shopEditForm.getId())
				.orElseThrow(() -> new RuntimeException("Shop not found"));
		MultipartFile imageFile = shopEditForm.getImageFile();

		// カテゴリ設定
		Category category = categoryService.findById(shopEditForm.getCategoryId())
				.orElseThrow(
						() -> new IllegalArgumentException("Invalid category ID: " + shopEditForm.getCategoryId()));
		shop.setCategory(category);

		// 古い画像の削除
		if (shop.getImageName() != null) {
			deleteOldImage(shop.getImageName());
		}

		if (!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("path/to/your/static/storage/" + hashedImageName);
			copyImageFile(imageFile, filePath);
			shop.setImageName(hashedImageName);
		}

		// その他のプロパティ設定
		shop.setShopName(shopEditForm.getShopName());
		shop.setDescription(shopEditForm.getDescription());
		shop.setPriceUpper(shopEditForm.getPriceUpper());
		shop.setPriceLower(shopEditForm.getPriceLower());
		shop.setHoursOpen(shopEditForm.getHoursOpen());
		shop.setHoursClose(shopEditForm.getHoursClose());
		shop.setClosedDay(shopEditForm.getClosedDay());
		shop.setPostalCode(shopEditForm.getPostalCode());
		shop.setAddress(shopEditForm.getAddress());
		shop.setPhoneNumber(shopEditForm.getPhoneNumber());

		shopRepository.save(shop);
	}

	// UUIDを使って生成したファイル名を返す
	public String generateNewFileName(String fileName) {
		String[] fileNames = fileName.split("\\.");
		String extension = fileNames[fileNames.length - 1]; // 拡張子を取得
		String hashedFileName = UUID.randomUUID().toString() + "." + extension; // UUIDに拡張子を結合
		return hashedFileName;
	}

	// 画像ファイルを指定したファイルにコピーする
	public void copyImageFile(MultipartFile imageFile, Path filePath) {
		try {
			Files.copy(imageFile.getInputStream(), filePath);
		} catch (IOException e) {
			throw new RuntimeException("Image upload failed", e); // 例外をラップして再スロー
		}
	}

	// 古い画像ファイルを削除
	public void deleteOldImage(String imageName) {
		Path oldImagePath = Paths.get("path/to/your/static/storage/" + imageName);
		try {
			Files.deleteIfExists(oldImagePath); // 画像ファイルがあれば削除
		} catch (IOException e) {
			throw new RuntimeException("Failed to delete old image", e); // 例外をラップして再スロー
		}
	}

	// 全店舗を取得する
	public List<Shop> findAll() {
		return shopRepository.findAll();
	}

	// IDで店舗を取得
	public Shop findById(Integer id) {
		return shopRepository.findById(id) // Integer をそのまま使う
				.orElseThrow(() -> new RuntimeException("Shop not found"));
	}

	// 店舗削除
	@Transactional
	public void delete(Integer id) {
	    shopRepository.deleteById(id);
	}

}
