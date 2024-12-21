package com.example.tabelog.form;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ShopRegisterForm {

	@NotNull(message = "カテゴリIDを入力してください。")
	private Long categoryId;

	@NotBlank(message = "店舗名を入力してください。")
	private String shopName;

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

	@NotBlank(message = "郵便番号を入力してください。")
	private String postalCode;

	@NotBlank(message = "住所を入力してください。")
	private String address;

	@NotBlank(message = "電話番号を入力してください。")
	private String phoneNumber;

	@NotNull(message = "定休日を選択してください。")
	private WeekDay closedDay; // String → WeekDay に変更
}