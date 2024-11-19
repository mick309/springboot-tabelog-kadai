package com.example.tabelog.form;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationInputForm {
    @NotNull(message = "予約日を入力してください。")
    private LocalDate reservationsDate;

    @NotNull(message = "予約時間を入力してください。")
    private LocalTime reservationTime;

    @Min(1)
    @Max(100)
    private Integer numberOfPeople;

    private Integer userId; // メソッドではなくフィールドを定義

    private static final LocalTime OPEN_TIME = LocalTime.of(9, 0);
    private static final LocalTime CLOSE_TIME = LocalTime.of(21, 0);

    public void validate() throws IllegalArgumentException {
        if (reservationTime.isBefore(getOpenTime()) || reservationTime.isAfter(getCloseTime().minusHours(1))) {
            throw new IllegalArgumentException("予約時間は営業開始から閉店1時間前の間に設定してください。");
        }
    }

    public static LocalTime getOpenTime() {
        return OPEN_TIME;
    }

    public static LocalTime getCloseTime() {
        return CLOSE_TIME;
    }
}
