package com.example.tabelog.entity;

import java.sql.Timestamp;
import java.time.LocalTime;

import com.example.tabelog.form.WeekDay;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "shop")
@Data
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	

	// Categoryの情報（カテゴリID）を外部キーで関連付け
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false) // 必須項目として設定
	private Category category;

	@Column(name = "shop_name", nullable = false, length = 255) // 必須項目として設定、長さを制限
	private String shopName;

	@Column(name = "image_name", length = 255) // 画像ファイル名、長さを制限
	private String imageName;

	@Column(name = "description", length = 1000) // 説明文、長さを制限
	private String description;

	@Column(name = "price_upper")
	private Integer priceUpper;

	@Column(name = "price_lower")
	private Integer priceLower;

	@Column(name = "hours_open")
	private LocalTime hoursOpen;

	@Column(name = "hours_close")
	private LocalTime hoursClose;

	@Enumerated(EnumType.STRING)
	@Column(name = "closed_day", nullable = false) // 曜日をEnumとして保存
	private WeekDay closedDay;

	@Column(name = "postal_code", length = 10) // 郵便番号の長さを制限
	private String postalCode;

	@Column(name = "address", length = 500) // 住所の長さを制限
	private String address;

	@Column(name = "phone_number", length = 15) // 電話番号の長さを制限
	private String phoneNumber;

	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt;

}
