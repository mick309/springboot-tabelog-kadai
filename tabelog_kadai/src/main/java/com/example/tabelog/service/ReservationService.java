package com.example.tabelog.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.example.tabelog.form.ReservationInputForm;

@Service

public class ReservationService {

	// 予約日と時間のバリデーションロジックを提供するメソッド
	public boolean isReservationDateValid(LocalDate date) {
		// 実装例: 本日以降の日付であれば true を返す
		return !date.isBefore(LocalDate.now());
	}

	public boolean isReservationTimeValid(LocalTime time) {
		// ロジック: 開店時間と閉店時間から有効な時間を判定
		return !(time.isBefore(ReservationInputForm.getOpenTime()) ||
				time.isAfter(ReservationInputForm.getCloseTime().minusHours(1)));
	}
}
