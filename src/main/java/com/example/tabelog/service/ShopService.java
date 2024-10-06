package com.example.tabelog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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

}
