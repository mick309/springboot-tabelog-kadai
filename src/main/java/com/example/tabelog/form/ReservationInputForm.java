package com.example.tabelog.form;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReservationInputForm {
	@NotNull(message = "予約日を入力してください。")
	private LocalDate reservationsDate;

	@NotNull(message = "予約時間を入力してください。")
	private LocalTime reservationTime;

	@NotNull(message = "人数を入力してください。")
	@Size(min = 1, message = "少なくとも1名を入力してください。")
	private Integer numberOfPeople;

	@NotNull(message = "店舗IDを入力してください。")
	
	private Integer shopId;
	
	private Integer userId() {
		return null;
	}


	// 既存の開店時間と閉店時間
	private static final LocalTime OPEN_TIME = LocalTime.of(9, 0);
	private static final LocalTime CLOSE_TIME = LocalTime.of(21, 0);

	public void validate() throws IllegalArgumentException {
		if (reservationTime.isBefore(getOpenTime()) || reservationTime.isAfter(getCloseTime().minusHours(1))) {
			throw new IllegalArgumentException("予約時間は営業開始から閉店1時間前の間に設定してください。");
		}
	}

	// Getters and Setters
	public LocalDate getReservationsDate() {
		return reservationsDate;
	}

	public void setReservationsDate(LocalDate reservationsDate) {
		this.reservationsDate = reservationsDate;
	}

	public LocalTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public Integer getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Integer numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getUserId() {
		return userId();
	}

	public void setUserId(Integer userId) {
	}


	public static LocalTime getOpenTime() {
		return OPEN_TIME;
	}

	public static LocalTime getCloseTime() {
		return CLOSE_TIME;
	}

	
}
