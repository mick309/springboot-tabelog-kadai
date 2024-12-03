package com.example.tabelog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

<<<<<<< HEAD
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

	// **店舗をIDで取得**
	public Shop findById(Integer id) {
		return shopRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Shop not found with ID: " + id));
	}

	// **店舗の検索 (キーワード対応)**
	public Page<Shop> searchByKeyword(String keyword, Pageable pageable) {
		return shopRepository.findByShopNameContainingOrAddressContaining(keyword, keyword, pageable);
	}

	// **全店舗をページネーションで取得**
	public Page<Shop> findAll(Pageable pageable) {
		return shopRepository.findAll(pageable);
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
	public void delete(Integer id) {
		Shop shop = shopRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Shop not found with ID: " + id));

		// 画像の削除
		if (shop.getImageName() != null) {
			deleteOldImage(shop.getImageName());
		}

		shopRepository.delete(shop);
	}

	// **画像ファイル名の生成**
	public String generateNewFileName(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
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
=======
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.tabelog.entity.Shop;
import com.example.tabelog.form.ShopEditForm;
import com.example.tabelog.form.ShopRegisterForm;
import com.example.tabelog.repository.ShopRepository;

@Service
public class ShopService {

	private final ShopRepository shopRepository;

	public ShopService(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	@Transactional
	public void create(ShopRegisterForm shopRegisterForm) {
		Shop shop = new Shop();
		MultipartFile imageFile = shopRegisterForm.getImageFile();

		if (!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
			copyImageFile(imageFile, filePath);
			shop.setImageName(hashedImageName);

		}
		shop.setCategoryId(shopRegisterForm.getCategoryId());
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
		Shop shop = shopRepository.getReferenceById(shopEditForm.getId());
		MultipartFile imageFile = shopEditForm.getImageFile();

		if (!imageFile.isEmpty()) {
			String imageName = imageFile.getOriginalFilename();
			String hashedImageName = generateNewFileName(imageName);
			Path filePath = Paths.get("src/main/resources/static/storage/" + hashedImageName);
			copyImageFile(imageFile, filePath);
			shop.setImageName(hashedImageName);
		}

		// shopEditForm インスタンスのメソッドを使用します
		shop.setCategoryId(shopEditForm.getCategoryId());
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
		for (int i = 0; i < fileNames.length - 1; i++) {
			fileNames[i] = UUID.randomUUID().toString();
		}
		String hashedFileName = String.join(".", fileNames);
		return hashedFileName;
	}

	// 画像ファイルを指定したファイルにコピーする
	public void copyImageFile(MultipartFile imageFile, Path filePath) {
		try {
			Files.copy(imageFile.getInputStream(), filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
}
