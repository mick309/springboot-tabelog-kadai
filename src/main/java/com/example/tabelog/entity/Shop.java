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

	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "shop_name")
	private String shopName;

	@Column(name = "image_name")
	private String imageName;

	@Column(name = "description")
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
	private WeekDay closedDay;

	@Column(name = "postal_code")
	private String postalCode;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "created_at", insertable = false, updatable = false)
	private Timestamp createdAt;

	@Column(name = "updated_at", insertable = false, updatable = false)
	private Timestamp updatedAt;

}
