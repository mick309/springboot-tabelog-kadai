package com.example.tabelog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List; // 必要なインポートを追加
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.tabelog.entity.Category;
import com.example.tabelog.entity.Shop;
import com.example.tabelog.form.ShopEditForm;
import com.example.tabelog.form.ShopRegisterForm;
import com.example.tabelog.repository.ShopRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ShopService {

	private final ShopRepository shopRepository;
	private final CategoryService categoryService;

	@Value("${file.upload.path}")
	private String storagePath;

	public ShopService(ShopRepository shopRepository, CategoryService categoryService) {
		this.shopRepository = shopRepository;
		this.categoryService = categoryService;
	}

	@PostConstruct
	public void initStoragePath() {
		Path storageDirectory = Paths.get(storagePath);
		try {
			if (!Files.exists(storageDirectory)) {
				Files.createDirectories(storageDirectory);
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to initialize storage directory: " + storagePath, e);
		}
	}

	// **店舗をIDで取得**
	public Shop findById(Long id) {
		return shopRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Shop not found with ID: " + id));
	}

	// **全店舗をページネーションで取得**
	public Page<Shop> getAllShops(Pageable pageable) {
		return shopRepository.findAll(pageable);
	}

	// **店舗の検索 (キーワード対応)**
	public Page<Shop> searchShopsByKeyword(String keyword, Pageable pageable) {
		if (keyword == null || keyword.isEmpty()) {
			return shopRepository.findAll(pageable); // キーワードがない場合は全件を返す
		}
		return shopRepository.findByShopNameLikeOrAddressLikeOrderByCreatedAtDesc(
				"%" + keyword + "%", "%" + keyword + "%", pageable);
	}

	// **店舗の新規登録**
	@Transactional
	public void create(ShopRegisterForm shopRegisterForm) {
		Shop shop = new Shop();
		MultipartFile imageFile = shopRegisterForm.getImageFile();

		// カテゴリの設定
		Category category = categoryService.findById(shopRegisterForm.getCategoryId())
				.orElseThrow(
						() -> new RuntimeException("Category not found with ID: " + shopRegisterForm.getCategoryId()));
		shop.setCategory(category);

		// 画像の保存
		if (imageFile != null && !imageFile.isEmpty()) {
			String imageName = generateNewFileName(imageFile.getOriginalFilename());
			copyImageFile(imageFile, imageName);
			shop.setImageName(imageName);
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

	// **店舗の更新**
	@Transactional
	public void update(ShopEditForm shopEditForm) {
		Shop shop = shopRepository.findById(shopEditForm.getId())
				.orElseThrow(() -> new RuntimeException("Shop not found with ID: " + shopEditForm.getId()));

		// カテゴリの更新
		Category category = categoryService.findById(shopEditForm.getCategoryId())
				.orElseThrow(() -> new RuntimeException("Category not found with ID: " + shopEditForm.getCategoryId()));
		shop.setCategory(category);

		// 画像の更新
		if (shopEditForm.getImageFile() != null && !shopEditForm.getImageFile().isEmpty()) {
			if (shop.getImageName() != null) {
				deleteOldImage(shop.getImageName());
			}
			String imageName = generateNewFileName(shopEditForm.getImageFile().getOriginalFilename());
			copyImageFile(shopEditForm.getImageFile(), imageName);
			shop.setImageName(imageName);
		}

		// その他のプロパティ更新
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

	// **店舗の削除**
    @Transactional
    public void delete(Long id) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found with ID: " + id));

        if (shop.getImageName() != null) {
            deleteOldImage(shop.getImageName());
        }

        shopRepository.delete(shop);
    }

	// **画像ファイル名の生成**
	public String generateNewFileName(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (!List.of("jpg", "jpeg", "png").contains(extension)) {
			throw new RuntimeException("Invalid file type. Only JPG and PNG are allowed.");
		}
		return UUID.randomUUID().toString() + "." + extension;
	}

	// **画像ファイルの保存**
	public void copyImageFile(MultipartFile imageFile, String targetFileName) {
		try {
			Path storageDirectory = Paths.get(storagePath);
			if (!Files.exists(storageDirectory)) {
				Files.createDirectories(storageDirectory);
			}
			Path targetPath = storageDirectory.resolve(targetFileName);
			Files.copy(imageFile.getInputStream(), targetPath);
		} catch (IOException e) {
			throw new RuntimeException("Image upload failed", e);
		}
	}

	// **古い画像ファイルの削除**
	public void deleteOldImage(String imageName) {
		Path imagePath = Paths.get(storagePath, imageName);
		try {
			if (Files.deleteIfExists(imagePath)) {
				System.out.println("画像を削除しました: " + imagePath.toAbsolutePath());
			} else {
				System.out.println("削除対象の画像が見つかりません: " + imagePath.toAbsolutePath());
			}
		} catch (IOException e) {
			throw new RuntimeException("画像の削除に失敗しました: " + e.getMessage(), e);
		}

	}

	public void save(Shop shop) {
		shopRepository.save(shop);

	}

}
