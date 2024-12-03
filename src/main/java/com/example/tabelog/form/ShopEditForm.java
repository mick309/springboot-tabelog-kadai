package com.example.tabelog.form;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

<<<<<<< HEAD
import com.example.tabelog.entity.Shop;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // デフォルトコンストラクタを自動生成
@AllArgsConstructor
@Data
public class ShopEditForm {

	@NotNull
	private Integer id;

	@NotNull(message = "カテゴリIDを入力してください。")
	private Integer categoryId;

	@NotBlank(message = "店舗名を入力してください。")
	private String shopName;

	private MultipartFile imageFile;

	@NotBlank(message = "説明を入力してください。")
	private String description;

	@NotNull(message = "価格帯(下限)を入力してください。")
	@Min(value = 1, message = "価格帯(上限)は1円以上に設定してください。")
	private Integer priceUpper;

	@NotNull(message = "価格帯(上限)を入力してください。")
	@Min(value = 1, message = "価格帯(下限)は1円以上に設定してください。")
	private Integer priceLower;

	@NotNull(message = "開店時間を入力してください。")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime hoursOpen;

	@NotNull(message = "閉店時間を入力してください。")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime hoursClose;

	@NotNull(message = "定休日を選択してください。")
	private WeekDay closedDay;

	@NotBlank(message = "郵便番号を入力してください。")
	private String postalCode;

	@NotBlank(message = "住所を入力してください。")
	private String address;

	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNumber;

	// Shopオブジェクトを受け取りフォームデータを初期化するコンストラクタ
	public ShopEditForm(Shop shop) {
		this.id = shop.getId();
		this.categoryId = shop.getCategory() != null ? shop.getCategory().getId() : null;
		this.shopName = shop.getShopName();
		this.imageFile = null; // 画像は新たにアップロードされるためnullで初期化
		this.description = shop.getDescription();
		this.priceUpper = shop.getPriceUpper();
		this.priceLower = shop.getPriceLower();
		this.hoursOpen = shop.getHoursOpen();
		this.hoursClose = shop.getHoursClose();
		this.closedDay = shop.getClosedDay();
		this.postalCode = shop.getPostalCode();
		this.address = shop.getAddress();
		this.phoneNumber = shop.getPhoneNumber();
	}
=======
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ShopEditForm {

	@NotNull
	private Integer id;

	@NotNull(message = "カテゴリIDを入力してください。")
	private Integer categoryId;

	@NotBlank(message = "店舗名を入力してください。")
	private String shopName;

	private MultipartFile imageFile;

	@NotBlank(message = "説明を入力してください。")
	private String description;

	@NotNull(message = "価格帯(上限)を入力してください。")
	@Min(value = 1, message = "価格帯(上限)は1円以上に設定してください。")
	private Integer priceUpper;

	@NotNull(message = "価格帯(下限)を入力してください。")
	@Min(value = 1, message = "価格帯(下限)は1円以上に設定してください。")
	private Integer priceLower;

	@NotNull(message = "開店時間を入力してください。")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime hoursOpen;

	@NotNull(message = "閉店時間を入力してください。")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime hoursClose;

	@NotNull(message = "定休日を選択してください。")
	private WeekDay closedDay;


	@NotBlank(message = "郵便番号を入力してください。")
	private String postalCode;

	@NotBlank(message = "住所を入力してください。")
	private String address;

	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNumber;

>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
}
