package com.example.tabelog.entity;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.tabelog.form.WeekDay;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "shop")
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_shop_category"))
	private Category category;

	@Column(name = "shop_name", nullable = false, length = 255)
	private String shopName;

	@Column(name = "image_name", length = 255)
	private String imageName;

	@Column(name = "description", length = 1000)
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
	@Column(name = "closed_day", nullable = false)
	private WeekDay closedDay;

	@Column(name = "postal_code", length = 10)
	private String postalCode;

	@Column(name = "address", length = 500)
	private String address;

	@Column(name = "phone_number", length = 15)
	private String phoneNumber;

	@Column(name = "retry_count", nullable = false, columnDefinition = "INT DEFAULT 0")
	private Integer retryCount = 0;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	// ✅ @PrePersist: 新規作成時
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
		if (this.retryCount == null) {
			this.retryCount = 0; // デフォルト値を設定
		}
	}

	// ✅ @PreUpdate: 更新時
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
=======
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
	private Long  id;
	

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

>>>>>>> branch 'main' of https://github.com/mick309/springboot-tabelog-kadai.git
}
